package Display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SplashScreen extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;

    public SplashScreen()
    {
    	/* Probeer de image te lezen, en sla deze op in de buffer in image. */
    	try
    	{         
    		image = ImageIO.read (new File ("splash.png"));
    	}
    	catch (IOException ex)
    	{
    		// handle exception...
    	}
    }

    @Override
    protected void paintComponent (Graphics g)
    {
        super.paintComponent (g);
        
        /* Teken het plaatje, dat in de buffer 'image' was opgeslagen. */
        g.drawImage (image, 0, 0, null);          
    }

}
