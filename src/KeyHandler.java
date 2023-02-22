import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GUI gui;
    public KeyHandler(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        //can also use if(keyEvent.isControlDown() && ... )
        if(keyEvent.isControlDown() && (keyEvent.getKeyCode() == KeyEvent.VK_S)){
            System.out.println("Save Keybind");
            gui.file.fileSave();
        }
        if(keyEvent.isShiftDown() && keyEvent.isControlDown() && (keyEvent.getKeyCode() == KeyEvent.VK_S)){
            System.out.println("Save as Keybind");
            gui.file.fileSaveAs();
        }
        if(keyEvent.isAltDown() && keyEvent.getKeyCode() == KeyEvent.VK_F){
            gui.fileMenu.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
