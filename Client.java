import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * 1)The Client class sends the message to the server.
 * 2)The client waits for the message to be received back.
 * 3)On receiving the message the client prints the message.
 * 4)After printing the client exits.
 */
public class Client
{

  private static Socket socket;

  public static void main(String args[])
  {
    try
    {
      String host = "localhost";
      int port = 25000;
      InetAddress address = InetAddress.getByName(host);

      //Creates a socket connection.
      socket = new Socket(address, port);

      //Creates an output stream object.
      OutputStream os = socket.getOutputStream();

      //Creates an output stream writer objecrt.
      OutputStreamWriter osw = new OutputStreamWriter(os);

      //Creates a buffered writer object.
      BufferedWriter bw = new BufferedWriter(osw);

      //Message to be sent to the server.
      String input = "This is my text to be changed by the SERVER ";
      String sendMessage = input + "\n";
      bw.write(sendMessage);
      bw.flush();
      System.out.println("Message sent to the server : "+sendMessage.trim());

      //Receiving message back from the server.
      InputStream is = socket.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String message = br.readLine();

      //Printing the received message from the server.
      System.out.println("Message received from the server : " +message);

    }


    //Catches the socket exception.
    catch (SocketException e) {
      System.out.println("Error in socket connection");
    }

    //Catches string exceptions.
    catch (StringIndexOutOfBoundsException e) {
      System.out.println("Out of range for the received message");
    }

    //Catches indexing errors.
    catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Index is out of range");
    }

    //Catches input and output errors.
    catch (IOException e) {
      System.out.println("Error with sending/receiving");
    }

    //Catches general exceptions.
    catch (Exception exception)
    {
      exception.printStackTrace();
    }
    finally
    {
      try
      {
        socket.close();
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }
  }
}