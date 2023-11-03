import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.Random;

public class Playlist extends Frame {
    final private Font titleFont = new Font("Verdana", Font.BOLD, 18);
    final private Font mainFont = new Font("Verdana", Font.PLAIN, 15);
    final private Font smallFont = new Font("Verdana", Font.PLAIN, 13);
    String input;
    int count = 0; // how many songs
    Song first = new Song(null, null, null);
    Song ptr = first;
    Song ptr2;
    int ctr = 1; // which song number we are on
    Song ptr3;
    Song prev;
    Song last;
    Song shufflePtr;
    int shuffleCount; // which shuffle song number we get
    int shuffleSongNum = 1;
    boolean playlistType = false; // when true it is in shuffle mode 
    public Playlist()
    {
        JLabel title = new JLabel("Create a playlist!");
        title.setFont(titleFont);
        title.setBackground(new Color(217,208,154));
        title.setHorizontalAlignment(JLabel.CENTER);

        JLabel questionOne = new JLabel("Playlist name: ");
        questionOne.setBackground(new Color(217,208,154));
        questionOne.setFont(mainFont);

        JTextField one = new JTextField();
        one.setFont(mainFont);

        JLabel questionTwo = new JLabel("Song name: ");
        questionTwo.setBackground(new Color(217,208,154));
        questionTwo.setFont(mainFont);

        JTextField two = new JTextField();
        two.setFont(mainFont);

        JButton add = new JButton("ADD");
        add.setFont(mainFont);
        JButton display = new JButton("Display Playlist");
        display.setFont(mainFont);

        JButton left = new JButton("<--");
        left.setBounds(20, 655, 50, 30);
        left.setBackground(new Color(217, 196, 158));
        add(left);

        JButton right = new JButton("-->");
        right.setBounds(270, 655, 50, 30);
        right.setBackground(new Color(217, 196, 158));
        add(right);

        ImageIcon icon = new ImageIcon("shuffle.png");
        Image ic = icon.getImage();
        Image iconScaled = ic.getScaledInstance(40, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(iconScaled);
        JButton shuffle = new JButton(scaledIcon);
        shuffle.setBounds(330, 655, 50, 30);
        shuffle.setBackground(new Color(217, 196, 158));
        add(shuffle);

        add.addActionListener(new ActionListener() { // creates linked list of songs

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                input = two.getText(); // storing what is typed into the text field
                if (count!=0)
                {
                    Song current = new Song(null, null,null);
                    current.setTitle(input);
                    
                    ptr.setNext(current);
                    current.setPrev(ptr); // makes it a DLL
                
                    // these two lines are causing a problem
                    first.setPrev(current);
                    current.setNext(first); // makes it a CDLL
    
                    ptr = ptr.getNext();
                    count++;
               }

               else
               {
                    first.setTitle(input);
                    first.setNext(first); //  makes it a CDLL
                    first.setPrev(first); // makes it a CDLL
                    ptr = first;
                    count++;
               }
               two.setText("");
            }  
        });

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(10,1,0,0));
        form.setBackground(new Color(217,208,154));
        form.setOpaque(false);

        JPanel list = new JPanel();
        list.setLayout(new GridLayout(0, 2, 0, 0));
        list.setBackground(new Color(217, 196, 158)); 
        list.setOpaque(true);
        //add(list);

        JScrollPane scrollList = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollList.setBounds(30, 465, 340, 180);
        scrollList.setBackground(new Color(217, 196, 158)); 
        scrollList.setOpaque(false);
        add(scrollList);

        ptr2 = first;

        JLabel pTitle = new JLabel("");
        pTitle.setFont(titleFont);
        pTitle.setBounds(150, 420, 300, 50);

        ptr3 = first;
        prev = null;
        JLabel currentSong = new JLabel(""); // for shuffling through songs
        currentSong.setFont(smallFont);
        currentSong.setBounds(170, 655, 100, 30);
        add(currentSong);

        display.addActionListener(new ActionListener() { // displays the list panel of all the songs

            @Override
            public void actionPerformed(ActionEvent a) {
                pTitle.setText(one.getText());
                currentSong.setText(ptr2.getTitle());
                while(ctr-1<count) // iterating through the list
                {
                    list.add(new JLabel("Song " + ctr + ": " + ptr2.getTitle()));
                    list.repaint();
                    list.revalidate();
                    ptr2 = ptr2.getNext();
                    ctr++;
                }
            }
            
        });

        Random rand = new Random();
        shufflePtr = first;
        
        left.addActionListener(new ActionListener() { 

            @Override
            public void actionPerformed(ActionEvent a) 
            {
                shufflePtr = first;
                shuffleSongNum = 1;
                if (playlistType==false)
                {
                    ptr3 = ptr3.getPrev();
                    currentSong.setText(ptr3.getTitle());
                }
                else
                {
                    shuffleCount = rand.nextInt(count) + 1;
                    while (shuffleSongNum<shuffleCount) // putting shufflePtr on the number of the song
                    {
                        shufflePtr = shufflePtr.getNext();
                        shuffleSongNum++;
                    }
                    // after this while loop, we start on a random song, instead of the first one
                    while (shufflePtr.getTitle().equals(currentSong.getText()))
                    {
                        shuffleSongNum = 1;
                        shuffleCount = rand.nextInt(count) + 1;
                        while (shuffleSongNum<shuffleCount)
                        {
                            shufflePtr = shufflePtr.getNext();
                            shuffleSongNum++;
                        }
                    }
                    currentSong.setText(shufflePtr.getTitle());
                }
            }
            
        });

        right.addActionListener(new ActionListener() { 

            @Override
            public void actionPerformed(ActionEvent a) 
            {
                shufflePtr = first;
                shuffleSongNum = 1;
                if (playlistType==false)
                {
                    ptr3 = ptr3.getNext();
                    currentSong.setText(ptr3.getTitle());
                }
                else
                {
                    shuffleCount = rand.nextInt(count) + 1;
                    while (shuffleSongNum<shuffleCount) // putting shufflePtr on the number of the song
                    {
                        shufflePtr = shufflePtr.getNext();
                        shuffleSongNum++;
                    }
                    // after this while loop, we start on a random song, instead of the first one
                    while (shufflePtr.getTitle().equals(currentSong.getText()))
                    {
                        shuffleSongNum = 1;
                        shuffleCount = rand.nextInt(count) + 1;
                        while (shuffleSongNum<shuffleCount)
                        {
                            shufflePtr = shufflePtr.getNext();
                            shuffleSongNum++;
                        }
                    }
                    currentSong.setText(shufflePtr.getTitle());
                }
            }
            
        });

        // problem: after clicking left or right many times it is going into an infinite loop?

        shuffle.addActionListener(new ActionListener() { 

            @Override
            public void actionPerformed(ActionEvent a) 
            {
                shufflePtr = first;
                shuffleSongNum = 1;
                if(playlistType==true)
                {
                    playlistType = false;
                    shuffle.setBackground(new Color(217, 196, 158));
                }
                else
                {
                    playlistType = true;
                    shuffle.setBackground(new Color(222, 197, 221));
                    shuffleCount = rand.nextInt(count) + 1;
                    while (shuffleSongNum<shuffleCount) // putting shufflePtr on the number of the song
                    {
                        shufflePtr = shufflePtr.getNext();
                        shuffleSongNum++;
                    }
                    // after this while loop, we start on a random song, instead of the first one
                    while (shufflePtr.getTitle().equals(currentSong.getText()))
                    {
                        shuffleSongNum = 1;
                        shuffleCount = rand.nextInt(count) + 1;
                        while (shuffleSongNum<shuffleCount)
                        {
                            shufflePtr = shufflePtr.getNext();
                            shuffleSongNum++;
                        }
                    }
                    currentSong.setText(shufflePtr.getTitle());
                }
            }
        });

        form.add(title);
        form.add(questionOne);
        form.add(one);
        form.add(questionTwo);
        form.add(two);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1,2,0,0));
        buttons.setBackground(new Color(217,208,154));
        buttons.setOpaque(false);
        buttons.add(add);
        buttons.add(display);

        form.add(buttons);

        add(pTitle);
        add(form);

        setMinimumSize(new Dimension(400,700));
        setResizable(true);
        setBackground(new Color(222, 205, 173));
        setTitle("My playlist");
        setVisible(true);
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() // when the x is clicked, the window closes and the application stops running
        {
            public void windowClosing(WindowEvent we)
            {
                dispose();
            }
        });
    }

    public static void main (String[]args)
    {
        new Playlist();
    }
}