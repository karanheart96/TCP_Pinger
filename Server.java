import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * 1)The server class receives the message from the client.
 * 2)The message is then reversed.
 * 3)The reversed message is finally converted to Uppercase.
 * 4)The Processed message is then sent back to the server.
 * 5)After server receives the message the client exits.
 */
public class Server
{

  private static Socket socket;

  public static void main(String[] args)
  {
    try
    {
      int port = 25000;
      ServerSocket serverSocket = new ServerSocket(port);
      System.out.println("Server Started and listening to the port 25000");

        //Reading the message from the client
        socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String input = br.readLine();
        System.out.println("Message received from client is "+input);
        String returnMessage;

        //Converting the received string to reverse and converting to upper case.
        StringBuilder input1 = new StringBuilder();
        input1.append(input);

        //Reverses the string.
        input1 = input1.reverse();
        String out = input1.toString();

        //String buffer object to hold the string.
        StringBuffer cap =  new StringBuffer(out);
        int ln = cap.length();

        //Changes the Capitalization.
        for (int i=0; i<ln; i++)
         {
            Character c = cap.charAt(i);
            if (Character.isLowerCase(c))
              cap.replace(i, i+1, Character.toUpperCase(c)+"");
            else
              cap.replace(i, i+1, Character.toLowerCase(c)+"");
         }

         //Return message is stored.
         returnMessage = cap.toString();

        //Creates an output stream object.
        OutputStream os = socket.getOutputStream();

        //Creates an output stream writer object.
        OutputStreamWriter osw = new OutputStreamWriter(os);

        //Creates a buffered writer object to hold the message.
        BufferedWriter bw = new BufferedWriter(osw);

        //Sends the message to the client.
        bw.write(returnMessage);

        //Sending the message back to the client.
        System.out.println("Message sent to the client is "+returnMessage);
        bw.flush();
    }

    //Catches socket exceptions.
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
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        socket.close();
      }
      catch(Exception e){}
    }
  }
}