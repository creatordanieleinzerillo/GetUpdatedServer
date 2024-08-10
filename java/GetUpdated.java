
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.ParseException;
import javax.print.attribute.standard.PageRanges;


public class GetUpdated {
    
    public static void main(String args[]){

        GitCommunications command = new GitCommunications();
        Runtime terminal = Runtime.getRuntime();
        File locationInfo = new File(System.getProperty("user.dir"), "locationinfo.json");
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {        
            obj = (JSONObject) parser.parse(new FileReader(locationInfo)); 
        } 
        catch(java.io.IOException | org.json.simple.parser.ParseException e) {
            System.err.println(e);
            return;
        }

        String dir = (String) obj.get("location");
        String key = (String) obj.get("token");
        String url = (String) obj.get("url");
        String serverPath = (String) obj.get("serverDir");
        String bot = (String) obj.get("bot");
        //prima apertura
        BufferedReader reader;
        command.pull(dir, terminal);
        try {
            //we are using this for private scope, comment from line 92 to line 96 to use this package for your purpose
            Process Minecraftino = terminal.exec(new String[]{"node", "."}, null, new File(bot));
            String check = "";
            reader = new BufferedReader(new InputStreamReader(Minecraftino.getInputStream()));
            while((check = reader.readLine()) != null)
                System.out.println(check);
            Process Server = terminal.exec(new String[]{"java" ,"-Xmx2G", "-jar","fabric-server-launch.jar"}, null, new File(serverPath));
            reader = new BufferedReader(new InputStreamReader(Server.getInputStream()));
            while((check = reader.readLine()) != null)
                System.out.println(check);
            //Server.waitFor();
        } catch (java.io.IOException e) {
            System.err.println("Errore durante esecuzione Server" + e.toString());
        }
        try {
            System.out.println(dir);
            command.gitadd(dir, terminal);
            command.gitCommit(dir, terminal);
            command.gitPush(key, url, dir, terminal);
        } catch (java.io.IOException | InterruptedException e) {
            System.err.println("Errore durante esecuzione comando gitr add" + e.toString());
        }
    }
}