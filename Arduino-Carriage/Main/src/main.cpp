#include <Arduino.h>
#include <WiFi.h>
#include <WebServer.h>

const char* ssid = "ENGG2K3K";
WiFiUDP udp;
unsigned int localUdpPort = 3020;  // Port used for communication
char incomingPacket[255];  // Buffer for incoming packet
const int PWMA = 14;
const int AIN1 = 27;
const int AIN2 = 26;
//TEST CHANGE
class WiFiManager {
  public:
  void connect() {
    WiFi.begin(ssid);
    while(WiFi.status() != WL_CONNECTED) {
      delay(1000);
      Serial.println("Connecting to WiFi...");
    }
    Serial.println("Connected to WiFi");
    Serial.print("ESP32 IP: ");
    Serial.println(WiFi.localIP());
  }

  IPAddress getIP() {
    return WiFi.localIP();
  }
};

class PacketManager {
  public:
  void begin() {
    udp.begin(localUdpPort);
    Serial.printf("Listening on port %d\n", localUdpPort);
  }
  String receivePacket() {
    int packetSize = udp.parsePacket();
    if (packetSize) {
      int len = udp.read(incomingPacket, 255);
      if (len > 0) {
        incomingPacket[len] = 0;
      }
      return String(incomingPacket);
    }
  }
  void sendPacket(IPAddress ip, int port, String message) {
      udp.beginPacket(ip, port);
      //udp.write(char_array);
      udp.endPacket();
  }
};

class CommandManager {
  public:
  MotionManager motionManager;
  MiscManager miscManager;

  String manage(String command) {
    String task = interpret(command);
    return execute(task);
  }
  String interpret(String command) {
    String task = command;
    return task;
  }
  String execute(String task) {
    return task;
  }
};

class MotionManager {
  public:
  void forward(int speed) {

  }
  void backward(int speed) {

  }
  void stop() {

  }
  void forwardStop() {

  }
  void backwardStop() {

  }

};

class MiscManager {
  public:
  void doorControl(boolean status) {

  }
  void ledControl(boolean status, int color) {

  }
  int sensorStatus() {
    int status;
    return status;
  }

};

WiFiManager wifiManager;
PacketManager packetManager;
CommandManager commandManager;

void setup() {
  Serial.begin(115200);
  wifiManager.connect();
  packetManager.begin();
}

void loop() {
  String command = packetManager.receivePacket();
  if(command != "") {
    Serial.printf("Received command: '%s'\n", command.c_str());
    String response = commandManager.manage(command);
    Serial.printf("Sending response: '%s'\n", response.c_str());
    packetManager.sendPacket(wifiManager.getIP(), localUdpPort, response);
  }
}