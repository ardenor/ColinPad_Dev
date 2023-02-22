import java.awt.*;

public class FormatMenuFunctions {

    GUI gui;
    Font arial, comicSansMS, timesNewRoman;
    String selectedFont;
    public FormatMenuFunctions(GUI gui) {
        this.gui = gui;
    }

    public void formatWordWrap(){

        if(gui.wordWrapOn == false){
            gui.wordWrapOn = true;
            gui.textArea.setLineWrap(true);
            gui.textArea.setWrapStyleWord(true);
            gui.itemWrap.setText("Word Wrap: On");
        } else if (gui.wordWrapOn == true){
            gui.wordWrapOn = false;
            gui.textArea.setLineWrap(false);
            gui.textArea.setWrapStyleWord(false);
            gui.itemWrap.setText("Word Wrap: Off");
        }
    }

    public void setFont(String font){
        selectedFont = font;

        switch(selectedFont){
            case "Arial": gui.textArea.setFont(arial); break;
            case "Comic Sans MS": gui.textArea.setFont(comicSansMS); break;
            case "Times New Roman": gui.textArea.setFont(timesNewRoman); break;
        }
    }

    public void createFont(int fontSize){
        arial = new Font("Arial", Font.PLAIN, fontSize);
        comicSansMS = new Font("Comic Sans MS", Font.PLAIN, fontSize);
        timesNewRoman = new Font("Times New Roman", Font.PLAIN, fontSize);

        setFont(selectedFont);
    }
}
