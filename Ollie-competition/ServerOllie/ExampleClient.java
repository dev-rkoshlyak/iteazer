import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class ExampleClient {
  private static String doCommand(BufferedReader input, OutputStreamWriter output, String command) throws IOException {
    output.write(command + "\n");
    output.flush();
    return input.readLine();
  }
  
  public static void main (String[] args) throws IOException {
    Socket clientSocket = new Socket("localhost", 32_321);
    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    OutputStreamWriter output = new OutputStreamWriter(clientSocket.getOutputStream());
    String ans = doCommand(input, output, "TeamA parol1");
    System.out.println("ans: " + ans);
    ans = doCommand(input, output, "connect");
    System.out.println("ans: " + ans);
    doCommand(input, output, "roll 0 50 1000");
    doCommand(input, output, "roll 90 50 1000");
    doCommand(input, output, "roll 180 50 1000");
    doCommand(input, output, "roll 270 50 1000");
    doCommand(input, output, "roll 0 0 1000");
    
    clientSocket.close();
  }
}
