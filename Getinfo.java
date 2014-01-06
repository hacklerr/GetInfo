package getInfo;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * User: rhackler
 * Date: 12/4/13
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Getinfo {

    JFrame frame = new JFrame("About your computer");
    JPanel display = new JPanel();
    int counter = 0;
    JLabel name;

    public Getinfo() throws SocketException {
                                                        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        display.setBackground(Color.blue);

        Font font = new Font("Courier", Font.BOLD, 12);

        display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));

        try {
            java.net.InetAddress i = java.net.Inet4Address.getLocalHost();
            name = new JLabel("Computer name:         " + i.getHostName());
            name.setFont(font);
            name.setForeground(Color.WHITE);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        display.add(name);


        Enumeration<NetworkInterface> interfaces = NetworkInterface
                .getNetworkInterfaces();

        for (NetworkInterface netIf : Collections.list(interfaces)) {
            if (netIf.isUp()) {

                Enumeration<InetAddress> hey = netIf.getInetAddresses();
                while (hey.hasMoreElements()) {
                    InetAddress addr = hey.nextElement();
                    if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                        counter++;
                        JLabel[] interfaceL = new JLabel[10];
                        interfaceL[counter] = new JLabel("Connected interface :  " + netIf.getDisplayName());
                        JLabel[] ipLable = new JLabel[10];
                        ipLable[counter] = new JLabel("IP address:            " + addr);

                        display.add(interfaceL[counter]);
                        display.add(ipLable[counter]);
                        ipLable[counter].setFont(font);
                        ipLable[counter].setForeground(Color.WHITE);
                        
                        interfaceL[counter].setFont(font);
                        interfaceL[counter].setForeground(Color.WHITE);

                    }
                }


                frame.setVisible(true);
//                frame.setSize(550, 150);
                frame.add(display);

                frame.pack();

}}}
    public static void main(String[] args) throws SocketException {

        new Getinfo();


    }
}
