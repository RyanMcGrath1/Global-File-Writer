import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import static java.lang.System.exit;

public class RawFileWriter {

    public static String path;
    private static boolean run = true;
    private static int interval;

    public static void main(String[] args) throws AWTException, IOException, InterruptedException {
        /*
          The amount of time between each message can be altered by changing the value in interval.
          The time is measured in milliseconds
          The code will break if little to no interval is used
        */

        if (args.length == 0) {
            path = "C:\\Users\\RyanM\\Documents\\example.txt";
        } else {
            path = args[0];
        }

        interval = 1500;
        Start();
    }

    //Take coordinates, moves mouse and clicks
    public static void click(int x, int y) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static LinkedList<String> loadFromTextFile(String pathName) {
        File file = new File(pathName);
        BufferedReader bufferedReader;
        String line;
        LinkedList<String> list = new LinkedList<>();

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            line = bufferedReader.readLine();
            while (line != null) {
                list.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static void Start() {
        // Might throw a UnsatisfiedLinkError if the native library fails to load or a RuntimeException if hooking fails
        GlobalMouseHook mouseHook = new GlobalMouseHook(); // Add true to the constructor, to switch to raw input mode

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("| Press [middle] mouse wheel to begin. Press [middle] mouse wheel again to terminate |");
        System.out.println("--------------------------------------------------------------------------------------\n\n");
        System.out.println("Global mouse hook successfully started. Connected mice:");

        for (Map.Entry<Long, String> mouse : GlobalMouseHook.listMice().entrySet()) {
            System.out.format("%d: %s\n", mouse.getKey(), mouse.getValue());
        }
        mouseHook.addMouseListener(new GlobalMouseAdapter() {

            @Override
            public void mousePressed(GlobalMouseEvent event) {
                if (event.getButton() == GlobalMouseEvent.BUTTON_MIDDLE) {
                    try {
                        Driver(event.getX(), event.getY());
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        try {
            while (run) {
                Thread.sleep(128);
            }
        } catch (InterruptedException e) {
            //Do nothing
        } finally {
            mouseHook.shutdownHook();
        }
    }

    //Calls the clipboard
    public static void copy(String input) {
        StringSelection stringSelection = new StringSelection(input);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        ClipboardOwner owner = (clipboard1, contents) -> {
        };
        clipboard.setContents(stringSelection, owner);
    }

    //Ctrl+V and Enter
    private static void paste() {
        try {
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_V);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_V);
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            System.out.println("Problem printing");
        }
    }


    public static void Driver(int x, int y) throws AWTException {

        //This section handles the termination of the script
        GlobalMouseHook mouseHook = new GlobalMouseHook();
        mouseHook.addMouseListener(new GlobalMouseAdapter() {
            @Override
            public void mousePressed(GlobalMouseEvent event) {
                if (event.getButton() == GlobalMouseEvent.BUTTON_MIDDLE) {
                    try {
                        run = false;
                        exit(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //This section acts as the Driver for copying/pasting
        click(x, y);
        LinkedList<String> output = new LinkedList<>(loadFromTextFile(path));
        output.forEach((line) -> {
            try {
                copy(line);
                paste();
                Thread.sleep(interval);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}

