public class EditMenuFunctions {
    GUI gui;

    public EditMenuFunctions(GUI gui) {
        this.gui = gui;
    }

    public void undo(){

        gui.um.undo();
    }

    public void redo(){
        gui.um.redo();
    }
}
