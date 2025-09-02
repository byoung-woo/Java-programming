import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ImageFilterApp extends JFrame {

    private BufferedImage originalImage;
    private BufferedImage filteredImage;

    private JLabel imageLabel;
    private JComboBox<String> filterComboBox1;
    private JComboBox<String> filterComboBox2;
    private JComboBox<String> filterComboBox3;
    private JComboBox<String> filterComboBox4;
    private JButton applyFilterButton;
    private JButton RandomButton;
    private JButton TimerRandomButton;
    private Random random = new Random();

    private Timer timer = new Timer();
    private boolean isTimerRunning = false;
    
    
    public ImageFilterApp() {
        super("Image Filter App");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);

        // UI ���� ��� �ʱ�ȭ
        initUI();
       

        // �̹����� �ҷ��� originalImage�� �Ҵ�
        
//        loadImage("C:/Users/bingw/Desktop/�ڹټҽ��ڵ�/images/Lenna512.jpg");
//        filteredImage = deepCopy(originalImage);
        loadImage1();

        // �̹��� ���͸��� ���� �ʱ�ȭ
        initializeFilteredImage();

        // UI�� �����ϰ� ȭ�鿡 ǥ��
        updateUI();
    }

    private void initUI() {
        imageLabel = new JLabel();
        filterComboBox1 = new JComboBox<>(new String[]{"original", "Grayscale", "Sepia", "Invert Colors"});
        filterComboBox2 = new JComboBox<>(new String[]{"original", "Grayscale", "Sepia", "Invert Colors"});
        filterComboBox3 = new JComboBox<>(new String[]{"original", "Grayscale", "Sepia", "Invert Colors"});
        filterComboBox4 = new JComboBox<>(new String[]{"original", "Grayscale", "Sepia", "Invert Colors"});
        applyFilterButton = new JButton("Apply Filter");
        RandomButton = new JButton("Random Filter");
        TimerRandomButton = new JButton("TimerRandom Filter");
        JPanel controlPanel = new JPanel();
        JPanel controlPanel2 = new JPanel();
        
        controlPanel.add(filterComboBox1);
        controlPanel.add(filterComboBox2);
        controlPanel.add(filterComboBox3);
        controlPanel.add(filterComboBox4);
        
        controlPanel2.add(applyFilterButton);
        controlPanel2.add(RandomButton);
        controlPanel2.add(TimerRandomButton);

        
        add(imageLabel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.NORTH);
        add(controlPanel2, BorderLayout.SOUTH);
        
        // ���� ���� ��ư�� ���� �̺�Ʈ ������ ���
        applyFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilter();
            }
        });
    
        RandomButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RandomButton();
        	}
        });
        
        TimerRandomButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		TimerRandomButton();
        	}
        });
}
    
    private void loadImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        originalImage = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB
        );
        Graphics2D g = originalImage.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
    }
    private BufferedImage deepCopy(BufferedImage image) {
        BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics g = copy.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return copy;
    }

    private void loadImage1() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                originalImage = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeFilteredImage() {
        if (originalImage != null) {
            // originalImage�� ������ ũ��� Ÿ���� BufferedImage ����
            filteredImage = new BufferedImage(
                    originalImage.getWidth(), originalImage.getHeight(), originalImage.getType());
        }
    }
    
    
    private void applyFilter() {// ���õ� ���Ϳ� ���� �̹��� ���͸� ����
        if (originalImage == null) {
            return;
        }
        String selectedFilter1 = (String) filterComboBox1.getSelectedItem();
        switch (selectedFilter1) {
        	case "original":
        		originalFilter1();
        		break;
        	case "Grayscale":
                applyGrayscaleFilter1();
                break;
            case "Sepia":
                applySepiaFilter1();
                break;
            case "Invert Colors":
                applyInvertColorsFilter1();
                break;
        }
        String selectedFilter2 = (String) filterComboBox2.getSelectedItem();
        switch (selectedFilter2) {
    		case "original":
    			originalFilter2();
    			break;
            case "Grayscale":
                applyGrayscaleFilter2();
                break;
            case "Sepia":
                applySepiaFilter2();
                break;
            case "Invert Colors":
                applyInvertColorsFilter2();
                break;
        }
        String selectedFilter3 = (String) filterComboBox3.getSelectedItem();
        switch (selectedFilter3) {
    	case "original":
    		originalFilter3();
    		break;
            case "Grayscale":
                applyGrayscaleFilter3();
                break;
            case "Sepia":
                applySepiaFilter3();
                break;
            case "Invert Colors":
                applyInvertColorsFilter3();
                break;
        }
        String selectedFilter4 = (String) filterComboBox4.getSelectedItem();
        switch (selectedFilter4) {
    	case "original":
    		originalFilter4();
    		break;
            case "Grayscale":
                applyGrayscaleFilter4();
                break;
            case "Sepia":
                applySepiaFilter4();
                break;
            case "Invert Colors":
                applyInvertColorsFilter4();
                break;
        }
        updateUI();
    }
    
    
    private void RandomButton() {// �������� �Լ� ���� �� ȣ��
        Runnable[] functions1 = {
                this::originalFilter1,
                this::applyGrayscaleFilter1,
                this::applySepiaFilter1,
                this::applyInvertColorsFilter1, 
        };
        Runnable[] functions2 = {
                this::originalFilter2,
                this::applyGrayscaleFilter2,
                this::applySepiaFilter2,
                this::applyInvertColorsFilter2, 
        };     
        Runnable[] functions3 = {
                this::originalFilter3,
                this::applyGrayscaleFilter3,
                this::applySepiaFilter3,
                this::applyInvertColorsFilter3, 
        };
        
        Runnable[] functions4 = {
                this::originalFilter4,
                this::applyGrayscaleFilter4,
                this::applySepiaFilter4,
                this::applyInvertColorsFilter4, 
        };
       
        int randomIndex1 = random.nextInt(functions1.length);
        functions1[randomIndex1].run();
        int randomIndex2 = random.nextInt(functions1.length);
        functions2[randomIndex2].run();      
        int randomIndex3 = random.nextInt(functions1.length);
        functions3[randomIndex3].run();       
        int randomIndex4 = random.nextInt(functions1.length);
        functions4[randomIndex4].run();
        
        updateUI();
    }
    
    private void TimerRandomButton() {//1�ʸ��� �����Լ� ȣ���ϴ� �Լ�
    	    if (isTimerRunning) {
    	        // Ÿ�̸Ӱ� �̹� ���� ���̸� �����մϴ�
    	        timer.cancel();
    	        isTimerRunning = false;
    	    } else {
    	        // Ÿ�̸Ӱ� ������� �ʰų� ��ҵ� ��� �� �ν��Ͻ� ����
    	        timer = new Timer();

    	        // ���ʸ��� ���� �Լ��� �����ϵ��� Ÿ�̸Ӹ� �����մϴ�
    	        timer.scheduleAtFixedRate(new TimerTask() {
    	            public void run() {
    	                // ���⼭ ���� �Լ��� ȣ���մϴ�
    	            	RandomButton();
    	            }
    	        }, 0, 1000);

    	        isTimerRunning = true;
    	    }
    	}
    
    private void originalFilter1() {//��������1
      for (int y = 0; y < (originalImage.getHeight()/2); y++) {
          for (int x = 0; x < (originalImage.getWidth()/2); x++) {
              int rgb = originalImage.getRGB(x, y);
              filteredImage.setRGB(x, y, rgb);
          }
      }
  }
  private void applyGrayscaleFilter1() {// �� �ȼ��� RGB ���� ������� �����Ͽ� ��� �̹��� ����
      for (int y = 0; y < (originalImage.getHeight()/2); y++) {
          for (int x = 0; x < (originalImage.getWidth()/2); x++) {
              int rgb = originalImage.getRGB(x, y);
              int gray = (int) (0.299 * ((rgb >> 16) & 0xFF) +
                      0.587 * ((rgb >> 8) & 0xFF) +
                      0.114 * (rgb & 0xFF));
              int grayRgb = (gray << 16) | (gray << 8) | gray;
              filteredImage.setRGB(x, y, grayRgb);
          }
      }
  }

  private void applySepiaFilter1() {
      // Sepia ���� ����
      for (int y = 0; y < (originalImage.getHeight()/2); y++) {
          for (int x = 0; x < (originalImage.getWidth()/2); x++) {
              int rgb = originalImage.getRGB(x, y);

              int r = (rgb >> 16) & 0xFF;
              int g = (rgb >> 8) & 0xFF;
              int b = rgb & 0xFF;

              int tr = (int) (0.393 * r + 0.769 * g + 0.189 * b);
              int tg = (int) (0.349 * r + 0.686 * g + 0.168 * b);
              int tb = (int) (0.272 * r + 0.534 * g + 0.131 * b);

              // ���� 255�� �ʰ��ϴ� ��� 255�� ����
              tr = Math.min(tr, 255);
              tg = Math.min(tg, 255);
              tb = Math.min(tb, 255);

              int sepiaRgb = (tr << 16) | (tg << 8) | tb;
              filteredImage.setRGB(x, y, sepiaRgb);
          }
      }
  }

  private void applyInvertColorsFilter1() {
      // Invert Colors ���� ����
      for (int y = 0; y < (originalImage.getHeight()/2); y++) {
          for (int x = 0; x < (originalImage.getWidth()/2); x++) {
              int rgb = originalImage.getRGB(x, y);

              int r = 255 - ((rgb >> 16) & 0xFF);
              int g = 255 - ((rgb >> 8) & 0xFF);
              int b = 255 - (rgb & 0xFF);

              int invertedRgb = (r << 16) | (g << 8) | b;
              filteredImage.setRGB(x, y, invertedRgb);
          }
      }
  }
//----------------------------
  private void originalFilter2() {
  	 for (int y = 0; y < (originalImage.getHeight()/2); y++) {
           for (int x = (originalImage.getWidth()/2); x < originalImage.getWidth(); x++) {
              int rgb = originalImage.getRGB(x, y);
              filteredImage.setRGB(x, y, rgb);
          }
      }
  }
  private void applyGrayscaleFilter2() {
      // �� �ȼ��� RGB ���� ������� �����Ͽ� ��� �̹��� ����
      for (int y = 0; y < (originalImage.getHeight()/2); y++) {
          for (int x = (originalImage.getWidth()/2); x < originalImage.getWidth(); x++) {
              int rgb = originalImage.getRGB(x, y);
              int gray = (int) (0.299 * ((rgb >> 16) & 0xFF) +
                      0.587 * ((rgb >> 8) & 0xFF) +
                      0.114 * (rgb & 0xFF));
              int grayRgb = (gray << 16) | (gray << 8) | gray;
              filteredImage.setRGB(x, y, grayRgb);
          }
      }
  }

  private void applySepiaFilter2() {
      // Sepia ���� ����
      for (int y = 0; y < (originalImage.getHeight()/2); y++) {
          for (int x = (originalImage.getWidth()/2); x < originalImage.getWidth(); x++) {
              int rgb = originalImage.getRGB(x, y);

              int r = (rgb >> 16) & 0xFF;
              int g = (rgb >> 8) & 0xFF;
              int b = rgb & 0xFF;

              int tr = (int) (0.393 * r + 0.769 * g + 0.189 * b);
              int tg = (int) (0.349 * r + 0.686 * g + 0.168 * b);
              int tb = (int) (0.272 * r + 0.534 * g + 0.131 * b);

              // ���� 255�� �ʰ��ϴ� ��� 255�� ����
              tr = Math.min(tr, 255);
              tg = Math.min(tg, 255);
              tb = Math.min(tb, 255);

              int sepiaRgb = (tr << 16) | (tg << 8) | tb;
              filteredImage.setRGB(x, y, sepiaRgb);
          }
      }
  }

  private void applyInvertColorsFilter2() {
      // Invert Colors ���� ����
      for (int y = 0; y < (originalImage.getHeight()/2); y++) {
          for (int x = (originalImage.getWidth()/2); x < originalImage.getWidth(); x++) {
              int rgb = originalImage.getRGB(x, y);

              int r = 255 - ((rgb >> 16) & 0xFF);
              int g = 255 - ((rgb >> 8) & 0xFF);
              int b = 255 - (rgb & 0xFF);

              int invertedRgb = (r << 16) | (g << 8) | b;
              filteredImage.setRGB(x, y, invertedRgb);
          }
      }
  }
  //-----------------------------
  private void originalFilter3() {
      for (int y =(originalImage.getHeight()/2); y < originalImage.getHeight(); y++) {
          for (int x = 0; x < (originalImage.getWidth()/2); x++) {
              int rgb = originalImage.getRGB(x, y);
              filteredImage.setRGB(x, y, rgb);
          }
      }
  }
  private void applyGrayscaleFilter3() {
      // �� �ȼ��� RGB ���� ������� �����Ͽ� ��� �̹��� ����
      for (int y = (originalImage.getHeight()/2); y < originalImage.getHeight(); y++) {
          for (int x = 0; x < (originalImage.getWidth()/2); x++) {
              int rgb = originalImage.getRGB(x, y);
              int gray = (int) (0.299 * ((rgb >> 16) & 0xFF) +
                      0.587 * ((rgb >> 8) & 0xFF) +
                      0.114 * (rgb & 0xFF));
              int grayRgb = (gray << 16) | (gray << 8) | gray;
              filteredImage.setRGB(x, y, grayRgb);
          }
      }
  }

  private void applySepiaFilter3() {
      // Sepia ���� ����
      for (int y =(originalImage.getHeight()/2); y < originalImage.getHeight(); y++) {
          for (int x = 0; x < (originalImage.getWidth()/2); x++) {
              int rgb = originalImage.getRGB(x, y);

              int r = (rgb >> 16) & 0xFF;
              int g = (rgb >> 8) & 0xFF;
              int b = rgb & 0xFF;

              int tr = (int) (0.393 * r + 0.769 * g + 0.189 * b);
              int tg = (int) (0.349 * r + 0.686 * g + 0.168 * b);
              int tb = (int) (0.272 * r + 0.534 * g + 0.131 * b);

              // ���� 255�� �ʰ��ϴ� ��� 255�� ����
              tr = Math.min(tr, 255);
              tg = Math.min(tg, 255);
              tb = Math.min(tb, 255);

              int sepiaRgb = (tr << 16) | (tg << 8) | tb;
              filteredImage.setRGB(x, y, sepiaRgb);
          }
      }
  }

  private void applyInvertColorsFilter3() {
      // Invert Colors ���� ����
      for (int y =(originalImage.getHeight()/2); y < originalImage.getHeight(); y++) {
          for (int x = 0; x < (originalImage.getWidth()/2); x++) {
              int rgb = originalImage.getRGB(x, y);

              int r = 255 - ((rgb >> 16) & 0xFF);
              int g = 255 - ((rgb >> 8) & 0xFF);
              int b = 255 - (rgb & 0xFF);

              int invertedRgb = (r << 16) | (g << 8) | b;
              filteredImage.setRGB(x, y, invertedRgb);
          }
      }
  }
//-------------------------
  private void originalFilter4() {
  	  for (int y = (originalImage.getHeight()/2); y < originalImage.getHeight(); y++) {
            for (int x = (originalImage.getWidth()/2); x < originalImage.getWidth(); x++) {
              int rgb = originalImage.getRGB(x, y);
              filteredImage.setRGB(x, y, rgb);
          }
      }
  }
  private void applyGrayscaleFilter4() {
      // �� �ȼ��� RGB ���� ������� �����Ͽ� ��� �̹��� ����
      for (int y = (originalImage.getHeight()/2); y < originalImage.getHeight(); y++) {
          for (int x = (originalImage.getWidth()/2); x < originalImage.getWidth(); x++) {
              int rgb = originalImage.getRGB(x, y);
              int gray = (int) (0.299 * ((rgb >> 16) & 0xFF) +
                      0.587 * ((rgb >> 8) & 0xFF) +
                      0.114 * (rgb & 0xFF));
              int grayRgb = (gray << 16) | (gray << 8) | gray;
              filteredImage.setRGB(x, y, grayRgb);
          }
      }
  }

  private void applySepiaFilter4() {
      // Sepia ���� ����
  	  for (int y = (originalImage.getHeight()/2); y < originalImage.getHeight(); y++) {
            for (int x = (originalImage.getWidth()/2); x < originalImage.getWidth(); x++) {
              int rgb = originalImage.getRGB(x, y);

              int r = (rgb >> 16) & 0xFF;
              int g = (rgb >> 8) & 0xFF;
              int b = rgb & 0xFF;

              int tr = (int) (0.393 * r + 0.769 * g + 0.189 * b);
              int tg = (int) (0.349 * r + 0.686 * g + 0.168 * b);
              int tb = (int) (0.272 * r + 0.534 * g + 0.131 * b);

              // ���� 255�� �ʰ��ϴ� ��� 255�� ����
              tr = Math.min(tr, 255);
              tg = Math.min(tg, 255);
              tb = Math.min(tb, 255);

              int sepiaRgb = (tr << 16) | (tg << 8) | tb;
              filteredImage.setRGB(x, y, sepiaRgb);
          }
      }
  }

  private void applyInvertColorsFilter4() {
      // Invert Colors ���� ����
  	  for (int y = (originalImage.getHeight()/2); y < originalImage.getHeight(); y++) {
            for (int x = (originalImage.getWidth()/2); x < originalImage.getWidth(); x++) {
              int rgb = originalImage.getRGB(x, y);

              int r = 255 - ((rgb >> 16) & 0xFF);
              int g = 255 - ((rgb >> 8) & 0xFF);
              int b = 255 - (rgb & 0xFF);

              int invertedRgb = (r << 16) | (g << 8) | b;
              filteredImage.setRGB(x, y, invertedRgb);
          }
      }
  }

//  
    private void updateUI() {
        // filteredImage�� imageLabel�� ǥ��
        ImageIcon icon = new ImageIcon(filteredImage);
        imageLabel.setIcon(icon);

        // JFrame ����
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ImageFilterApp().setVisible(true);
            }
        });
    }
}
