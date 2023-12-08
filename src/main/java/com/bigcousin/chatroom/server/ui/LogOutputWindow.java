package main.java.com.bigcousin.chatroom.server.ui;

import main.java.com.bigcousin.chatroom.data.messages.enums.MessageType;
import main.java.com.bigcousin.chatroom.data.messages.enums.MessageSource;
import main.java.com.bigcousin.chatroom.data.messages.SystemMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogOutputWindow extends JFrame {
    private JTextArea textArea;
    private JTextField inputField;

    public LogOutputWindow() {
        // 窗口设置...

        textArea = new JTextArea();
        textArea.setEditable(false);
        // 获取系统默认字体并应用
        textArea.setFont(Font.getFont(Font.SANS_SERIF));

        JScrollPane scrollPane = new JScrollPane(textArea);

        inputField = new JTextField();
        // 获取系统默认字体并应用
        inputField.setFont(Font.getFont(Font.SANS_SERIF));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(inputField, BorderLayout.SOUTH);
        // 创建输入框
        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 当用户在输入框中按下 Enter 键时，将输入的文本追加到文本区域中
                SystemMessage message = new SystemMessage(inputField.getText(),
                        MessageType.INFO,
                        MessageSource.MANAGER);
                appendSystemMessage(message);
                // 清空输入框
                inputField.setText("");
            }
        });
        setTitle("系统日志");
        // 设置窗口大小和关闭操作...
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void appendSystemMessage(SystemMessage message) {
        // 根据消息类型设置颜色
        textArea.setForeground(message.getColor());

        // 表明来源
        String source = "[" + message.getSource() + "] ";

        // 在文本区域追加文本
        textArea.append(source + message.getContent() + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LogOutputWindow logOutputWindow = new LogOutputWindow();
            logOutputWindow.setVisible(true);
            SystemMessage systemMessage = new SystemMessage("日志测试",
                    MessageType.INFO,  MessageSource.SYSTEM);
            logOutputWindow.appendSystemMessage(systemMessage);
        });
    }
}