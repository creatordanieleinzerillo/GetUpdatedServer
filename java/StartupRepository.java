import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import GetUpdated.GitCommunications;


public class StartupRepository {
    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserisci directory da inserire nella repository");
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
        String rep = (String) obj.get("url");
        try {
            dir = reader.readLine();
            System.out.println("Inserisci link repository senza https");
            rep = reader.readLine();
            System.out.println("Inserisci chiave");
            key = reader.readLine();
        } catch (Exception e) {
            System.err.println("Errore nella raccolta informazioni");
        }
        GitCommunications comms = new GitCommunications();
        try {
            comms.gitadd(dir, Runtime.getRuntime());
            comms.gitCommit(dir, Runtime.getRuntime());
            comms.gitPush(key, rep, dir, Runtime.getRuntime());
        } catch (Exception e) {
            System.err.println("Errore durante comando (git add)");
        }
    }
}
