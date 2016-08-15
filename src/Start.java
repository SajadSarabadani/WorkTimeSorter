import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by sajad on 4/10/2016.
 */
public class Start {


    public static void main(String[] args) {


        JFrame jFrame = new JFrame();

        jFrame.pack();
        jFrame.setSize(500, 500);
        jFrame.setTitle("Schedule 1.0.2");
        jFrame.setIconImage(new ImageIcon("C:\\Users\\user\\ideaProjects\\Schedule\\src\\schedule.png").getImage());

        JTextArea textField = new JTextArea();
        jFrame.add(textField);
        jFrame.add(new JScrollPane(textField));

        //TextField should not accept any keyboard ui
        textField.setEditable(false);


        textField.addKeyListener(new KeyListener() {

            long lastDate;
            int totalSecondKilled, counter;
            String totalTimeKilled, totalTimeWorked;

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == 116) {

                    DateFormat dateFormat = DateFormat.getTimeInstance();
                    Date date = new Date();
                    Calendar calendar = Calendar.getInstance();

                    if (counter == 1) {

                        textField.append(String.valueOf(date) + "\n\n");
                        textField.append(dateFormat.format(date));
                        textField.append("\n\n");

                        calendar.setTimeInMillis(date.getTime() - new Date(lastDate).getTime());

                        textField.append("Hours : " + String.valueOf(calendar.get(Calendar.HOUR) - 3) + "   Minutes: "
                                + String.valueOf(calendar.get(Calendar.MINUTE) - 30) + "   Seconds: " + String.valueOf(calendar.get(Calendar.SECOND)) + "\n\n");


                        totalSecondKilled += ((calendar.get(Calendar.SECOND)) + ((calendar.get(Calendar.MINUTE) - 30) * 60) + ((calendar.get(Calendar.HOUR) - 3) * 3600));


                        totalTimeKilled = String.valueOf(totalSecondKilled / 3600) + ":" + String.valueOf((totalSecondKilled % 3600) / 60) + ":" + String.valueOf((totalSecondKilled % 3600) % 60);
                        System.out.println(totalTimeKilled);

                        counter = 0;


                    } else {

                        textField.append(String.valueOf(date) + "\n");
                        lastDate = date.getTime();
                        counter++;

                    }


                } else if (e.getKeyCode() == 112) {

                    System.out.println(totalTimeKilled);
                    System.out.println(textField.getText());
                    BufferedWriter writer = null;

                    Scanner scanner = new Scanner(new StringReader(textField.getText()));


                    try {

                        Date date = new Date();
                        DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
                        File file = new File(System.getProperty("user.home") + "\\desktop\\" + String.valueOf(format.format(date)) + ".txt");

                        writer = new BufferedWriter(new FileWriter(file, false));

                        while (scanner.hasNextLine()) {

                            writer.write(scanner.nextLine());
                            writer.newLine();

                        }

//                        int totalWorkTime = 32400;
//                        int temp = totalWorkTime - totalSecondKilled;

                        writer.newLine();
                        writer.newLine();
                        writer.newLine();
                        writer.write("Total Time Killed ->  " + (String.valueOf(totalSecondKilled / 3600) + ":" + String.valueOf((totalSecondKilled % 3600) / 60) + ":" + String.valueOf((totalSecondKilled % 3600) % 60)));
                        writer.newLine();
                        textField.append("\n The text file made in " + System.getProperty("user.home") + "\\desktop\\" + " directory\n");

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } finally {

                        if (writer != null)
                            try {
                                writer.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                    }

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        jFrame.setVisible(true);


    }

}
