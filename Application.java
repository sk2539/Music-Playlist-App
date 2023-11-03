import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Application extends JFrame
{
    final private Font mainFont = new Font("Verdana", Font.BOLD, 18);
    JTextField playlistName, songName;
    JLabel title;

    public void initialize()
    {
        JLabel questionOne = new JLabel("Playlist name: ");
        questionOne.setFont(mainFont);

        playlistName = new JTextField();
        playlistName.setFont(mainFont);

        JLabel songTitles = new JLabel("Song: ");
        songTitles.setFont(mainFont);

        songName = new JTextField();
        songName.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 1, 5, 5));
        formPanel.add(questionOne);
        formPanel.add(playlistName);
        formPanel.add(songTitles);
        formPanel.add(songName);

        title = new JLabel();
        title.setFont(mainFont);

        /*JButton btnADD = new JButton("ADD");
        btnADD.setFont(mainFont);
        btnADD.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
            }
            
        });*/

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(128, 128, 255));
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(title, BorderLayout.CENTER);

        setTitle("Make a playlist!");
        setSize(500,600);
        setMinimumSize(new Dimension(300,400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main (String[]args)
    {
        Application myFrame = new Application();
        myFrame.initialize();

    }
}
