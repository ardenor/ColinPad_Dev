import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    JFrame window;

    //TEXT AREA
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapOn = false;

    //TOP MENU BAR
    JMenuBar menuBar;
    JMenu fileMenu, editMenu, formatMenu, colorMenu;

    //FILE MENU
    JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemExit;

    //EDIT MENU
    JMenuItem itemUndo, itemRedo;

    //FORMAT MENU
    JMenu itemFont, itemFontSize;
    JMenuItem itemWrap, itemFontArial, itemFontCSMS, itemFontTNR;
    JMenuItem itemFontSize8, itemFontSize12, itemFontSize16, itemFontSize20, itemFontSize24, itemFontSize28;

    //COLOR MENU
    JMenuItem itemColor1, itemColor2, itemColor3, itemColor4;

    FileMenuFunctions file = new FileMenuFunctions(this);
    FormatMenuFunctions format = new FormatMenuFunctions(this);
    ColorMenuFunctions color = new ColorMenuFunctions(this);
    EditMenuFunctions edit = new EditMenuFunctions(this);

    KeyHandler keyHandler = new KeyHandler(this);

    UndoManager um = new UndoManager();

    public static void main(String[] args) {

        //new ColinPrint("testing");
        new GUI ();
    }

    public GUI(){ //constructor for all the GUI, calls sub creation methods for components

        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createColorMenu();

        //sets default settings
        format.selectedFont = "Arial";
        format.createFont(16);
        format.formatWordWrap();
        color.changeColor("Black");

        window.setVisible(true);
    }

    public void createWindow(){

        window = new JFrame("ColinPad");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void createTextArea(){
        textArea = new JTextArea();
        textArea.setFont(format.arial);
        textArea.setForeground(Color.white);

//        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("forest.jpeg"));
//        textArea.setImage(icon);

        textArea.addKeyListener(keyHandler);

        textArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    public void undoableEditHappened(UndoableEditEvent e){
                        um.addEdit(e.getEdit());
                    }
                });

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);
    }

    public void createMenuBar(){
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        formatMenu = new JMenu("Format");
        colorMenu = new JMenu("Color");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(colorMenu);

        window.setJMenuBar(menuBar);
    }

    public void createFileMenu(){
        itemNew = new JMenuItem("New");
        itemNew.addActionListener(this);
        itemNew.setActionCommand("New");

        itemOpen = new JMenuItem("Open");
        itemOpen.addActionListener(this);
        itemOpen.setActionCommand("Open");

        itemSave = new JMenuItem("Save");
        itemSave.addActionListener(this);
        itemSave.setActionCommand("Save");

        itemSaveAs = new JMenuItem("Save As");
        itemSaveAs.addActionListener(this);
        itemSaveAs.setActionCommand("SaveAs");

        itemExit = new JMenuItem("Exit");
        itemExit.addActionListener(this);
        itemExit.setActionCommand("Exit");

        fileMenu.add(itemNew);
        fileMenu.add(itemOpen);
        fileMenu.add(itemSave);
        fileMenu.add(itemSaveAs);
        fileMenu.add(itemExit);
    }

    public void createEditMenu(){
        itemUndo = new JMenuItem("Undo");
        itemUndo.addActionListener(this);
        itemUndo.setActionCommand("Undo");

        itemRedo = new JMenuItem("Redo");
        itemRedo.addActionListener(this);
        itemRedo.setActionCommand("Redo");

        editMenu.add(itemUndo);
        editMenu.add(itemRedo);
    }

    public void createFormatMenu(){
        itemWrap = new JMenuItem("Word Wrap: Off");
        itemWrap.addActionListener(this);
        itemWrap.setActionCommand("WordWrap");

        itemFont = new JMenu("Font");

        itemFontArial = new JMenuItem("Arial");
        itemFontArial.addActionListener(this);
        itemFontArial.setActionCommand("Arial");
        itemFont.add(itemFontArial);

        itemFontCSMS = new JMenuItem("Comic Sans MS");
        itemFontCSMS.addActionListener(this);
        itemFontCSMS.setActionCommand("ComicSansMS");
        itemFont.add(itemFontCSMS);

        itemFontTNR = new JMenuItem("Times New Roman");
        itemFontTNR.addActionListener(this);
        itemFontTNR.setActionCommand("TimesNewRoman");
        itemFont.add(itemFontTNR);

        itemFontSize = new JMenu("Font Size");

        itemFontSize8 = new JMenuItem("8");
        itemFontSize8.addActionListener(this);
        itemFontSize8.setActionCommand("8");
        itemFontSize12 = new JMenuItem("12");
        itemFontSize12.addActionListener(this);
        itemFontSize12.setActionCommand("12");
        itemFontSize16 = new JMenuItem("16");
        itemFontSize16.addActionListener(this);
        itemFontSize16.setActionCommand("16");
        itemFontSize20 = new JMenuItem("20");
        itemFontSize20.addActionListener(this);
        itemFontSize20.setActionCommand("20");
        itemFontSize24 = new JMenuItem("24");
        itemFontSize24.addActionListener(this);
        itemFontSize24.setActionCommand("24");
        itemFontSize28 = new JMenuItem("28");
        itemFontSize28.addActionListener(this);
        itemFontSize28.setActionCommand("28");

        itemFontSize.add(itemFontSize8);
        itemFontSize.add(itemFontSize12);
        itemFontSize.add(itemFontSize16);
        itemFontSize.add(itemFontSize20);
        itemFontSize.add(itemFontSize24);
        itemFontSize.add(itemFontSize28);

        formatMenu.add(itemWrap);
        formatMenu.add(itemFont);
        formatMenu.add(itemFontSize);

    }

    public void createColorMenu(){

        itemColor1 = new JMenuItem("White");
        itemColor1.addActionListener(this);
        itemColor1.setActionCommand("White");

        itemColor2 = new JMenuItem("Black");
        itemColor2.addActionListener(this);
        itemColor2.setActionCommand("Black");

        itemColor3 = new JMenuItem("Blue");
        itemColor3.addActionListener(this);
        itemColor3.setActionCommand("Blue");

        itemColor4 = new JMenuItem("Other");
        itemColor4.addActionListener(this);
        itemColor4.setActionCommand("Other");

        colorMenu.add(itemColor1);
        colorMenu.add(itemColor2);
        colorMenu.add(itemColor3);
        colorMenu.add(itemColor4);
    }

    @Override
    public void actionPerformed(ActionEvent e) { //overridden from ActionListener implementation
        System.out.println("action performed!!!");

        String command = e.getActionCommand();

        switch(command){
            case "New": file.fileNew(); break;
            case "Open": file.fileOpen(); break;
            case "Save": file.fileSave(); break;
            case "SaveAs": file.fileSaveAs(); break;
            case "Exit": file.fileExit(); break;
            case "Undo": edit.undo(); break;
            case "Redo": edit.redo(); break;
            case "WordWrap": format.formatWordWrap(); break;
            case "Arial": format.setFont("Arial"); break;
            case "ComicSansMS": format.setFont("Comic Sans MS"); break;
            case "TimesNewRoman": format.setFont("Times New Roman"); break;
            case "8": format.createFont(8); break;
            case "12": format.createFont(12); break;
            case "16": format.createFont(16); break;
            case "20": format.createFont(20); break;
            case "24": format.createFont(24); break;
            case "28": format.createFont(28); break;
            case "White": color.changeColor("White"); break;
            case "Black": color.changeColor("Black"); break;
            case "Blue": color.changeColor("Blue"); break;
            case "Other": color.changeColor("Other"); break;
        }
    }
}
