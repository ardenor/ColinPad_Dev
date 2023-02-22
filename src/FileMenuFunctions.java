import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileMenuFunctions {

    GUI gui;
    String fileName, fileAddress;

    public FileMenuFunctions(GUI gui){
        this.gui = gui;
    }

    public void fileNew(){
        gui.textArea.setText("");
        gui.window.setTitle("New");
        fileName = null;
        fileAddress = null;
    }

    public void fileOpen(){
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        if(fd.getFile() != null){ //if the file dialog gets SOMETHING
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();

            gui.window.setTitle(fileName);
        }

        System.out.println("File address and file name to be loaded: " + fileAddress + fileName);

        try{
            BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName)); //need full address of the file to be read
            gui.textArea.setText(""); //clears text on load

            String line = null;
            while((line = br.readLine()) != null){ //sets line to whatever is being read per line
                gui.textArea.append(line + "\n");
            }
            br.close();

        } catch(Exception e){
            System.out.println("File not opened: \n" + e);
        }
    }

    public void fileSaveAs(){
        FileDialog fd = new FileDialog(gui.window, "Save As", FileDialog.SAVE);
        fd.setVisible(true);

        if(fd.getFile() != null){
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();

            gui.window.setTitle(fileName);
        }

        try{
            FileWriter fw = new FileWriter(fileAddress + fileName); //writes to new file of this address/name
            fw.write(gui.textArea.getText()); //writes the contents of the textArea
            fw.close();

        } catch (Exception e){
            System.out.println("Something went wrong: \n" + e);
        }
    }

    public void fileSave(){
        if(fileName == null){
            fileSaveAs();
        } else {
            try{
                FileWriter fw = new FileWriter(fileAddress + fileName); //writes to new file of this address/name
                fw.write(gui.textArea.getText()); //writes the contents of the textArea
                gui.window.setTitle(fileName);
                fw.close();
            } catch (Exception e){
                System.out.println("Something went wrong: \n" + e);
            }
        }
    }

    public void fileExit(){
        System.exit(0);
    }
}
