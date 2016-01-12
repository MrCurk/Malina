package mr.curk.mail;

import java.io.*;

public class ConfigFile {
    private String mailTo = null;
    private String mailFrom = null;
    private String username = null;
    private String password = null;
    private String mailDebug = "false";
    private String mailSmtpHost = null;

    public ConfigFile(String file) {
        parseFile(file);
    }

    public String getMailTo() {
        return mailTo;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMailDebug() {
        return mailDebug;
    }
    public boolean getMailDebugBoolean() {
        return mailDebug.toUpperCase().equals("TRUE");
    }

    public String getMailSmtpHost() {
        return mailSmtpHost;
    }

    // branje datoteke
    private void parseFile(String fileName) {
        File file = new File(fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                parseLine(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Config file not found!!!");
        }
    }

    // parsanje config file
    private void parseLine(String line) {
        String[] token = line.split(";");

        switch (token[0].toUpperCase()) {
            case "USERNAME":
                username = token[1].trim();
                break;
            case "PASSWORD":
                password = token[1].trim();
                break;
            case "MAILTO":
                mailTo = token[1].trim();
                break;
            case "MAITLFROM":
                mailFrom = token[1].trim();
                break;
            case "MAILDEBUG":
                mailDebug = token[1].trim();
                break;
            case "MAILSMTPHOST":
                mailSmtpHost = token[1].trim();
                break;
        }
    }
}