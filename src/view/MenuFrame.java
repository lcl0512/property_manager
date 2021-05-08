package view;

import utils.CSVUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class MenuFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel label = new JLabel("请选择");
    JButton viewButton = new JButton("查看资产");
    JButton updateButton = new JButton("更新资产");
    JButton exitButton = new JButton("退出");
    static {
        try {
            //可跨平台风格，忽略时的默认风格
            String lookAndFeel = UIManager.getSystemLookAndFeelClassName();//当前系统风格
            UIManager.setLookAndFeel(lookAndFeel);

        }catch (Exception e) {

        }
    }
    MenuFrame(){
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        this.setVisible(true);
    }

    public void addActionEvent() {
        updateButton.addActionListener(this);
        exitButton.addActionListener(this);
        viewButton.addActionListener(this);
    }

    private void addComponentsToContainer() {
        container.add(label);
        container.add(exitButton);
        container.add(updateButton);
        container.add(viewButton);
    }

    private void setLocationAndSize() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dimension.width/3,dimension.height*3/4);
        this.setLocation((dimension.width-this.getWidth())/2,dimension.height/10);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension buttonDim = new Dimension(this.getWidth()/4,this.getHeight()/10);
        label.setSize(buttonDim);
        label.setLocation((this.getWidth()-label.getWidth())/2,(this.getHeight()/10));
        label.setFont(new Font(null,Font.BOLD,label.getHeight()/2));
        exitButton.setSize(buttonDim);
        updateButton.setSize(buttonDim);
        viewButton.setSize(buttonDim);
        Font font = new Font(null,Font.PLAIN,viewButton.getHeight()/3);
        viewButton.setFont(font);
        viewButton.setFocusPainted(false);
        updateButton.setLocation((this.getWidth()-updateButton.getWidth())/2,(this.getHeight()*2/4));
        updateButton.setFont(font);
        updateButton.setFocusPainted(false);
        exitButton.setFont(font);
        exitButton.setFocusPainted(false);
        exitButton.setLocation((this.getWidth()-updateButton.getWidth())/2,(this.getHeight()*3/4));
        viewButton.setLocation((this.getWidth()-updateButton.getWidth())/2,(this.getHeight()/4));

    }

    private void setLayoutManager() {
        container.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==viewButton){
            this.dispose();
            new MainFrame();
        }else if(e.getSource()==updateButton){
            if(JOptionPane.showConfirmDialog(null, "请选择更新后的CSV文件", "选择打开文件", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV Documents", "csv"));
                fileChooser.setAcceptAllFileFilterUsed(true);
                int result = fileChooser.showOpenDialog(this);
                if(result==JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (selectedFile != null) {
                        try {
                            CSVUtil.encryptFile(selectedFile);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "数据更新成功");
                    }
                }
            }

        }else if(e.getSource()==exitButton){
            int i=JOptionPane.showConfirmDialog(null, "是否真的退出系统",
                    "退出确认对话框",JOptionPane.YES_NO_CANCEL_OPTION);
            //通过对话框中按钮的选择来决定结果，单机yes时，窗口直接消失
            if(i==0)
                this.dispose();
        }
    }

}
