package service;

import javax.swing.*;
import java.io.*;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

/**
 * Created by Vidushka on 19/05/17.
 */
public class CopyService {
    private Path sourcePath = null;
    private Path destinationPath = null;
    private Long startTime;
    private Long endTime;
    private Long fileSize;
    private File src;
    private File dst;
    private Boolean flag;
    private InputStream in;
    private OutputStream out;

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public void startCoping(String from, String to, String copyOrMove) {
        src = new File(from);
        dst = new File(to);
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dst);
            long expectedBytes = src.length();
            long totalBytesCopied = 0;
            byte[] buf = new byte[1024];
            int len = 0;
            int progress;

            startTime = System.currentTimeMillis();
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            if (copyOrMove.equals("Move")) {
                boolean delete = src.delete();
            }
            endTime = System.currentTimeMillis();
            JOptionPane.showMessageDialog(null, copyOrMove + " Completed.\n" + (endTime - startTime) + "ms");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (AccessDeniedException e) {
            JOptionPane.showMessageDialog(null, "Cannot " + copyOrMove + " to this location");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


