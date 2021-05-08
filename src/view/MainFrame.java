package view;

import utils.CSVUtil;
import utils.Property;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.TableUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    JPanel panelTop = new JPanel();    //创建面板
    JComboBox comboBox=new JComboBox();    //area
    JComboBox posComboBox=new JComboBox();    //position
    JComboBox nameComboBox=new JComboBox();    //property name
    static {
        try {
            //可跨平台风格，忽略时的默认风格
            String lookAndFeel = UIManager.getSystemLookAndFeelClassName();//当前系统风格
            UIManager.setLookAndFeel(lookAndFeel);

        }catch (Exception e) {

        }
    }
    JScrollPane panelCenter = new JScrollPane();
    JPanel panelBottom = new JPanel();
    JButton saveButton = new JButton("导出数据");
    JTable table;
    Object [] colums = {"区域名称","存放地点","资产编码", "资产名称","规格型号","数量","单价","总价","购置日期" };
    Object [] colums2 = {"区域名称","资产名称","规格型号","数量","单价","总价","购置日期"};
    String [] area = {"崇礼楼","达高楼","行知楼","清华楼","同方楼","同源楼"};
    String[][] pos = {{"崇礼楼二楼连廊", "音乐厅"},
            {"达高楼五楼509仓库", "达高楼五楼", "达高楼五楼项目实训室五", "达高楼五楼项目实训室三", "达高楼五楼项目实训室六", "达高楼五楼项目实训室二", "达高楼四楼工作室", "达高楼五楼项目实训室一", "达高楼四楼项目实训室九", "达高楼五楼创意工作室二", "达高楼四楼项目实训室七", "达高楼四楼项目实训室十", "达高楼五楼原SKY工作室", "达高楼五楼创意工作室一", "达高楼五楼项目实训室四", "达高楼四楼项目实训室八"},
            {"X503"}, {"清华楼3409教室", "清华楼3403教室", "清华楼3414教室", "清华楼3404教室", "清华楼3415教室", "清华楼四楼教室", "清华楼3401教室", "清华楼3412教室", "清华楼3402教室", "清华楼3413教室", "清华楼3407教室", "清华楼3408教室", "清华楼3406教室", "清华楼3410教室", "清华楼3411教室"},
            {"同方楼2309教室", "同方楼2405机房", "同方楼2407机房", "同方楼2310教室", "同方楼2406机房", "同源楼二楼"},
            {"同源楼四楼", "同源楼一楼", "同源楼四楼7410", "同源楼一楼7113", "同源楼四楼7411", "同源楼四楼7412", "同源楼四楼7413", "同源楼四楼7414", "同源楼四楼7415", "同源楼四楼7416", "同源楼四楼7418", "同源楼四楼7419", "同源楼二楼7209", "同源楼二楼7205", "同源楼二楼7206", "同源楼二楼7207", "同源楼二楼7208", "同源楼五楼", "同源楼三楼7314", "同源楼三楼7315", "同源楼三楼7312", "同源楼三楼7313", "同源楼五楼7511", "同源楼二楼", "同源楼一楼7108", "同源楼一楼7109", "同源楼一楼7106", "同源楼一楼7107", "同源楼一楼7105", "同源楼四楼7421", "同源楼四楼7401", "同源楼四楼7402", "同源楼四楼7424", "同源楼四楼7403", "同源楼四楼7404", "同源楼四楼7405", "同源楼四楼7407", "同源楼四楼7408", "同源楼四楼7409", "同源楼三楼7304", "同源楼一楼7121", "同源楼三楼7302", "同源楼三楼", "同源楼三楼7307", "同源楼三楼7308", "同源楼二楼7210", "同源楼三楼7306"}
    };
    String[][] propertyName = {
            { "钢琴","人声效果器","话筒","监听对讲控制器","专业录音师耳机","空调","音箱支架及托盘","音频接口","投影幕布","学生椅","专业有源监听音箱","音频线","投影仪","舞台音响调音台","4芯电缆线","舞台激光灯","舞台帕灯","舞台反监听音响","录音设备","话筒放大器","动画配音员耳机","舞台背景墙","舞台航架","移动课桌椅（椅子）","舞台主音箱","均衡器","落地电扇","无线麦克","舞台par灯","耳机分配器","主功率放大器","监听功率放大器","灯光控制台"},
            { "电视机","VR设计工作站","VR颗粒云资料源库","会议桌","空调","学生椅","VR场景编辑器","VR眼镜头盔","交换机","电脑","锐秀互动台","投影仪","交互式HMD套件","学生桌","办公椅","电脑桌","服务器","硬盘塔","办公桌","机柜图腾","学生凳"},
            { "电视机","电脑","学生椅","灯光控台","提词器","办公桌","12路直通箱","平板柔光灯"},
            { "画架 ","显示器","话筒","空调","激光笔","中控台","音箱吊架","投影幕布","移动蓝色课桌椅","音箱","窗帘","电脑","投影仪","功放","办公椅","固定连排课桌椅","黑板","展示柜","画桌","学生凳"},
            { "电脑","话筒","空调","方凳","办公椅","电脑桌","黑板","拷贝台","交换机","学生凳"},
            { "调音台","话筒","静物台","文件柜 ","无线话筒","投影幕布","会议系统","音箱","电钢琴","摄像机","三脚架","投影仪","学生桌","电脑桌","教学琴架","鞋架","小会议桌","侧柜","矮柜","显示器","打印机","中控台","音响","格林移动工作位","服务器","格林工作站","办公桌","会议桌","幕布","空调","教学踏板","学生椅","相机","教学监听耳机","音箱设备","线拍仪","沙发","画架","方凳","电钢琴教室控制系统","教学MIDI键盘","黑板","电视机","画架 ","文件柜","移动课桌椅（桌子）","移动课桌椅（椅子）","电脑","无线投影系统器","功放","麦克","办公椅","固定桌椅","茶几","学生凳","教学数字接口","画桌"},
    };
    int areaIndex = 0;
    int posIndex =0;
    int nameIndex = 0;
    List<Property> properties = null;
    MainFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        panelTop.setBackground(Color.GRAY);
//        panelCenter.setBackground(Color.RED);
        System.out.println(panelCenter.getWidth()+", "+panelCenter.getHeight());
        System.out.println(table.getWidth()+", "+ table.getHeight());
//        this.pack();
        this.setVisible(true);
    }

    private void addActionEvent() {
        comboBox.addActionListener(this);
        posComboBox.addActionListener(this);
        nameComboBox.addActionListener(this);
        saveButton.addActionListener(this);
    }

    private void addComponentsToContainer() {
        addComboBox();
        addTable();
        panelBottom.add(saveButton);
        container.add(panelTop, BorderLayout.NORTH);
        container.add(panelCenter, BorderLayout.CENTER);
        container.add(panelBottom, BorderLayout.SOUTH);
    }

    private void setLocationAndSize() {
       this.setTitle("main window");
        // comboBox
        Dimension dim = new Dimension(300,34);
        comboBox.setSize(300,40);
        comboBox.setPreferredSize(dim);
        posComboBox.setSize(300,40);
        posComboBox.setPreferredSize(dim);
        nameComboBox.setSize(300,40);
        nameComboBox.setPreferredSize(dim);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int)(dimension.getWidth()*0.8),(int)(dimension.height*0.8));
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 10);
        this.setLocation(x,y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        container.setSize(this.getSize());
        panelTop.setSize(container.getWidth(),container.getHeight()/5);
        panelCenter.setSize(container.getWidth(),container.getHeight()*3/5);
        panelBottom.setSize(container.getWidth(), container.getHeight()/5);
        saveButton.setSize(300,40);
        saveButton.setFont(new Font(null,Font.PLAIN,32));
    }

    private void setLayoutManager() {
        container.setLayout(new BorderLayout());
    }

    private void addComboBox(){
        JLabel areaLabel=new JLabel("区域");    //创建标签
        comboBox.addItem("--请选择--");    //向下拉列表中添加一项
        for(String s : area)
            comboBox.addItem(s);
        panelTop.add(areaLabel);
        panelTop.add(comboBox);
        JLabel posLabel=new JLabel("存放地点");    //创建标签
        posComboBox.addItem("--请选择--");    //向下拉列表中添加一项

        panelTop.add(posLabel);
        panelTop.add(posComboBox);
        JLabel nameLabel=new JLabel("资产名称");    //创建标签
        nameComboBox.addItem("--请选择--");    //向下拉列表中添加一项
        for(String name: propertyName[0]){
            nameComboBox.addItem(name);
        }
        panelTop.add(nameLabel);
        panelTop.add(nameComboBox);
    }
    public void addTable() {
        try {
            CSVUtil.readCSV("data/property.encrypted");
        } catch (Exception e) {
            e.printStackTrace();
        }
        properties = CSVUtil.filter("清华楼");
        CSVUtil.summarize(properties);
        areaIndex = 4;
        comboBox.setSelectedIndex(areaIndex);
        Object [][] data = CSVUtil.list2Array2(properties);
        table = new JTable(data,colums2);
        table.setRowHeight(40);
        table.getTableHeader().setFont(new Font(null,Font.BOLD,16));
        table.setFont(new Font(null, Font.PLAIN, 14));
        panelCenter.setViewportView(table);
    }
    public void addTableContent(){
        CSVUtil.summarize(properties);
        Object[][] data = CSVUtil.list2Array(properties);
        table = new JTable(data,colums);
        table.setRowHeight(40);
        table.getTableHeader().setFont(new Font(null,Font.BOLD,16));
        table.setFont(new Font(null, Font.PLAIN, 14));
        panelCenter.setViewportView(table);
    }
    public void addTableContent2(){
        CSVUtil.summarize(properties);
        Object[][] data = CSVUtil.list2Array2(properties);
        table = new JTable(data,colums2);
        table.setRowHeight(40);
        table.getTableHeader().setFont(new Font(null,Font.BOLD,16));
        table.setFont(new Font(null, Font.PLAIN, 14));
        panelCenter.setViewportView(table);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==comboBox){
            System.out.println(comboBox.getSelectedIndex()+":"+comboBox.getSelectedItem());
            if((areaIndex=comboBox.getSelectedIndex())!=0){
                // position combobox
                int count = posComboBox.getItemCount();
                System.out.println(count);
                posComboBox.setSelectedIndex(0);
                for (int j = count - 1; j > 0; --j)
                    posComboBox.removeItemAt(j);
                for (String s : pos[areaIndex - 1])
                    posComboBox.addItem(s);
                /*property name combobox*/
                count = nameComboBox.getItemCount();
                System.out.println(count);
                nameComboBox.setSelectedIndex(0);
                for (int j = count - 1; j > 0; --j)
                    nameComboBox.removeItemAt(j);
                for (String s : propertyName[areaIndex - 1])
                    nameComboBox.addItem(s);
                properties = CSVUtil.filter(area[areaIndex - 1]);
                addTableContent2();

            }
        }
        if(actionEvent.getSource()==posComboBox){
            System.out.println(posComboBox.getSelectedIndex()+":"+posComboBox.getSelectedItem());
            if((posIndex=posComboBox.getSelectedIndex())!=0){
                properties = CSVUtil.filter(area[areaIndex - 1], pos[areaIndex-1][posIndex-1]);
                addTableContent();
            }
        }
        if(actionEvent.getSource()==nameComboBox){
            System.out.println(nameComboBox.getSelectedIndex()+":"+nameComboBox.getSelectedItem());
            if((nameIndex=nameComboBox.getSelectedIndex())!=0){
               properties = CSVUtil.filter(area[areaIndex-1],posIndex==0 ? null : pos[areaIndex-1][posIndex-1],propertyName[areaIndex-1][nameIndex-1]);
               addTableContent();
            }
        }
        if(actionEvent.getSource()==saveButton){
            System.out.println("导出数据");
            for(Property property: properties){
                System.out.println(property);
            }
            if(JOptionPane.showConfirmDialog(null, "导出CSV文件", "导出CSV文件", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                JFileChooser fileChooser = new JFileChooser();
//                System.out.println(System.getProperty("user.dir"));
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/data"));
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//                fileChooser.showSaveDialog(this);
                fileChooser.setSelectedFile(new File("new.csv"));
                fileChooser.setFileFilter(new FileNameExtensionFilter("CSV File","csv"));
                fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
                fileChooser.setDialogTitle("导出CSV文件");
                int result = fileChooser.showDialog(this, "保存");
                while (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (selectedFile.exists() || new File(selectedFile.getName() + ".csv").exists()) {
                        JOptionPane.showMessageDialog(null, "文件已存在，请重新命名");
                        result = fileChooser.showDialog(this, "保存");
                    } else {

                        if (!selectedFile.getName().endsWith("csv")) {
                            fileChooser.setSelectedFile(new File(fileChooser.getName() + ".csv"));
                        }
                        if (posComboBox.getSelectedIndex() != 0) {
                            try {
                                CSVUtil.writeCSV(fileChooser.getSelectedFile(), properties);
                            } catch (IOException e) {
                                System.out.println("导出数据失败");
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                CSVUtil.writeCSV2(fileChooser.getSelectedFile(), properties);
                            } catch (IOException e) {
                                System.out.println("导出数据失败");
                                e.printStackTrace();
                            }
                        }
                        JOptionPane.showMessageDialog(this, "导出数据成功");
                        break;
                    }
                }
            }
        }

    }
}
