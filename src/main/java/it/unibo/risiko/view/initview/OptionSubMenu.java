package it.unibo.risiko.view.initview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A panel representing the principal menu's sub menu
 * @author Keliane Nana
 */
public class OptionSubMenu extends JPanel{
    private String screenResolution="700x700";
    private ButtonGroup grp;
    private ImageIcon backgroundImage = new ImageIcon("src\\main\\resources\\it\\unibo\\risiko\\images\\background.jpg");

    public OptionSubMenu(PrincipalMenu p, InitialView g) {
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(350,0,0,0));
        this.setPreferredSize(new Dimension(700, 700));
        //creating risolution button
        JButton risolution=p.addButtonToMenu("Resolution",this);
        //creating risolution option panel
        JPanel risPane = p.addPanelToMenu(this);
        this.add(Box.createRigidArea(new Dimension(0,5)));
        grp=createRisolutions(risPane);
        risPane.setLayout(new BoxLayout(risPane, BoxLayout.PAGE_AXIS));
        risPane.setBorder(BorderFactory.createEmptyBorder(0,25,0,21));
        //laying out rules button
        JButton regolamento=p.addButtonToMenu("Game Rules", this);
        //creation of the panel containing the buttons back and save.
        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(new Color(0,0,0,0));
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        //laying out the buttons back and save
        JButton back=p.addButtonToMenu("Back",buttonPane);
        buttonPane.add(Box.createRigidArea(new Dimension(20, 0)));
        JButton save=p.addButtonToMenu("Save",buttonPane);
        //adding buttonPane to the OptionMenu
        this.add(Box.createRigidArea(new Dimension(0,15)));
        this.add(buttonPane);
        //button lay out ajustments
        risolution.setBorder(BorderFactory.createEmptyBorder(12,73,12,74));
        regolamento.setBorder(BorderFactory.createEmptyBorder(12,69,12,69));
        //adding ActionListeners to buttons
        risolution.addActionListener(e->risPane.setVisible(true));
        regolamento.addActionListener(e->showRules());
        save.addActionListener(e->saveOption(p,risPane,g));
        back.addActionListener(e->g.updatePanel(p));
    }

    /**
     * This method shows the game's rules
     */
    private void showRules() {
        String rules=getGameRules();
        if(!rules.isEmpty()){
            //creating a JPanel which will contains the game rules
            JPanel rulePane=new JPanel();
            rulePane.setPreferredSize(new Dimension(500,500));
            rulePane.setLayout(new BorderLayout());
            JTextArea ruleText=new JTextArea(rules);
            JScrollPane logTextScroller=new JScrollPane(ruleText);
            rulePane.add(logTextScroller);
            //adding rulePane to a messageDialog JOptionPane
            JOptionPane.showMessageDialog(this,rulePane,"Game Rules", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * This method helps to get the game's rules text
     * @return a string that contains the rules of the game
     */
    private String getGameRules() {
        StringBuilder rules = new StringBuilder();
        BufferedReader bufferReader = null;
        String line, fileName="src\\main\\resources\\it\\unibo\\risiko\\gameRules\\rules.txt";

        try{ bufferReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
            while ((line = bufferReader.readLine()) != null) {
                rules.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while trying to read the file", "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            try {
                if (bufferReader != null) {
                    bufferReader.close(); 
                }
            } catch (IOException e) {
                e.printStackTrace(); 
            }
        }
        return rules.toString();
    }

    /**
     * Set the GameFrame's risolution with the selected risolution
     * @param p the parent principal menu of the OptionSubMenu
     * @param pane the risolution panel of the OptionSubMenu
     */
    private void saveOption(final PrincipalMenu p,final JPanel pane, final InitialView g) {
        screenResolution=grp.getSelection().getActionCommand();
        String ris[]=screenResolution.split("x");
        g.setResolution(Integer.parseInt(ris[0]), Integer.parseInt(ris[1]));
        pane.setVisible(false);
    }

    /**
     * Creates a radio button and add it to a specific ButtonGroup and panel
     * @param g the ButtonGroup
     * @param p the JPanel
     * @param text the radioButton's actionComand
     */
    private void createRadioButton(final ButtonGroup g,final JPanel p,final String text){
        JRadioButton r=new JRadioButton(text);
        r.setActionCommand(text);
        g.add(r);
        p.add(r);
    }
    /**
     * Creates sub panels that help in laying radio in the rigth way
     * @return a panel with a LINE_AXIS BoxLayout
     */
    private JPanel createResolutionSubPanels(){
        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
        return p1;
    }
    
    /**
     * This method creates a complete risolution panel
     * @param p the panel in which we should add the missing elements
     * @return the ButtonGroup containing all the radio that represent
     * the possible risolutions
     */
    private ButtonGroup createRisolutions(final JPanel p) {
        ButtonGroup g=new ButtonGroup();
        JPanel p1=createResolutionSubPanels();
        JPanel p2=createResolutionSubPanels();
        createRadioButton(g,p1,"1600x900");
        createRadioButton(g,p1,"1820x1080");
        createRadioButton(g,p2,"1800x950");
        createRadioButton(g,p2,"1024x1024");
        p.add(p1);
        p.add(p2);
        return g;
    }
}