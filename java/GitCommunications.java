import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class GitCommunications {
   
    public int pull(String location, Runtime terminal) {
        int check = 0;
        try {
            
            String[] pullCommand = {"git", "pull"};
            Process pull = terminal.exec(pullCommand, null, new File(location));
            pull.waitFor();
            BufferedReader ErrorStream = new BufferedReader(new InputStreamReader(pull.getErrorStream()));
            check = pull.waitFor();
            String lineCheck = "";
            while((lineCheck = ErrorStream.readLine()) != null){
                System.out.println(lineCheck);
            }
            
        } catch (java.io.IOException FailedCommand) {
            System.err.println("Errore durante l'esecuzione del comando! " + FailedCommand.toString());
            
            return 1;
        } catch (InterruptedException e){
            System.out.println("Comando terminato prima del previsto");
            return 1;
        }
        return check;
    }
        

    public int clone(String url, String key, String location, Runtime terminal){
        int check = 0;
        try {
            
            String[] cloneCommand = {"git", "clone" , "https://" + key + "@" + url , location};
            Process gitclone = terminal.exec(cloneCommand);
            BufferedReader ErrorStream = new BufferedReader(new InputStreamReader(gitclone.getErrorStream()));
            check = gitclone.waitFor();
            String lineCheck = "";
            while((lineCheck = ErrorStream.readLine()) != null){
                System.out.println(lineCheck);
            }
            System.out.println(check == 0);
        } catch (java.io.IOException e) {
            System.err.println("Errore durante esecuzione comando: (git clone) " + e.toString());
            return 1;
        } catch (InterruptedException CommandInterruptedException){
            System.err.println("Comando Interrotto! (git clone)");
            return 1;
        }
        
        return check;
    }   

    public int gitadd(String location, Runtime terminal) throws IOException, InterruptedException{
        String[] cmdAdd = {"git", "add", "."};
        Process addProcess = terminal.exec(cmdAdd, null, new File(location));
        BufferedReader NormalStream = new BufferedReader(new InputStreamReader(addProcess.getInputStream()));
        BufferedReader ErrorStream = new BufferedReader(new InputStreamReader(addProcess.getErrorStream()));
        String line = "";
        while((line = NormalStream.readLine()) != null)
            System.out.println(line);
        while((line = ErrorStream.readLine()) != null)
            System.out.println(line);
        return 0;
    }

    public int gitCommit(String location, Runtime terminal) throws IOException, InterruptedException{
        Process commit = terminal.exec(new String[]{"git", "commit", "-m", "Automatic"}, null, new File(location));
        BufferedReader NormalStream = new BufferedReader(new InputStreamReader(commit.getInputStream()));
        BufferedReader ErrorStream = new BufferedReader(new InputStreamReader(commit.getErrorStream()));
        String line = "";
        while((line = NormalStream.readLine()) != null)
            System.out.println(line);
        while((line = ErrorStream.readLine()) != null)
            System.out.println(line);
        return 0;
    }

    public int gitPush(String key, String url, String location, Runtime terminal) throws IOException, InterruptedException {
        Process push = Runtime.getRuntime().exec(new String[]{"git", "push" , "https://" + key + "@" + url}, null, new File(location));
        BufferedReader NormalStream = new BufferedReader(new InputStreamReader(push.getInputStream()));
        BufferedReader ErrorStream = new BufferedReader(new InputStreamReader(push.getErrorStream()));
        String line = "";
        while((line = NormalStream.readLine()) != null)
            System.out.println(line);
        while((line = ErrorStream.readLine()) != null)
            System.out.println(line);
        return 0;
    }
}
