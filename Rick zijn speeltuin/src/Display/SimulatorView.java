package Display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Other.Counter;
import Other.DataWrapper;
import Simulator.Main;
import Views.ViewController;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * A graphical view of the simulation grid.
 * The view displays a colored rectangle for each location 
 * representing its contents. It uses a default background color.
 * Colors for each type of species can be defined using the
 * setColor method.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
@SuppressWarnings("rawtypes")
public class SimulatorView extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;

    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;

    private final String STEP_PREFIX = "Step: ";
    private final String POPULATION_PREFIX = "Population: ";
    private JLabel stepLabel, population;
    private FieldView fieldView;
    
    // A map for storing colors for participants in the simulation
    private Map<Class, Color> colors;
    
    // A statistics object computing and storing simulation information
    private FieldStats stats;
    
    /**
     * Create a view of the given width and height.
     * @param height The simulation's height.
     * @param width  The simulation's width.
     */
    public SimulatorView(int height, int width)
    {
        stats	= new FieldStats();
        colors	= new LinkedHashMap<Class, Color>();

        setTitle ("Fox and Rabbit Simulatie");
        
        stepLabel	= new JLabel(STEP_PREFIX, JLabel.CENTER);
        population	= new JLabel(POPULATION_PREFIX, JLabel.CENTER);
        
        /* Window listener maken, zodat de windowClosing event kan worden opgevangen. */
        addWindowListener (new WindowAdapter ()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	/* Zodra de jFrame word afgesloten, word eerst de thread gestopt. Indien dit niet gebeurd, zal het schermpje opnieuw worden geopent. */
            	Main.getThread().stopThread();
            	
            	/* Het programma forceren om te sluiten, als dit nog niet is gebeurd. */
            	System.exit (1);
            }
        });
        
        setLocation (100, 50);
   		
        JPanel frame = new JPanel();
        
        frame.setLayout(new BorderLayout());
        frame.setBorder(new EmptyBorder(10, 10, 10, 10));
        
    	fieldView = new FieldView(height, width);
    	
    	JMenuBar menu = new JMenuBar();
    	
    	
    	
    	JMenu menu1 = new JMenu("Menu 1");
    	menu.add(menu1);
    	
    	
    	
    	
    	
    	/*
    	 * Menu voor de views.
    	 */
    	JMenu menuViews = new JMenu ("Views");
    	
    	JMenuItem viewHistog = new JMenuItem ("Histogram");
    	JMenuItem viewCirkel = new JMenuItem ("Cirkeldiagram");
    	JMenuItem viewHistor = new JMenuItem ("Lijndiagram");
    	//openMenuItem.addActionListener(this);
    	
		viewHistog.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent action)
			{
				JFrame frame = new JFrame();
				
				frame.setTitle ("Histogram");
				
				frame.setSize (500, 600);
				
				frame.add (ViewController.histoGram);
				
				frame.setVisible (true);
			}
		});
		
		viewCirkel.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent action)
			{
				JFrame frame = new JFrame();
				
				frame.setTitle ("Cirkeldiagram");
				
				frame.setSize (500, 300);
				
				frame.add (ViewController.pieChart);
				
				frame.setVisible (true);
			}
		});
		
		viewHistor.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent action)
			{
				JFrame frame = new JFrame();
				
				frame.setTitle ("Lijndiagram");
				
				frame.setSize (650, 425);
				
				frame.add (ViewController.lineChart);
				
				frame.setVisible (true);
			}
		});
    	
    	
    	menuViews.add (viewHistog);
    	menuViews.add (viewCirkel);
    	menuViews.add (viewHistor);
    	
    	menu.add (menuViews);
    	
    	
    	
    	
    	JMenu help = new JMenu("Help");
    	menu.add(help);
    	
    	
    	
    	
    	
    	JPanel field = new JPanel();
    	field.setLayout(new BorderLayout());
    	
    	
    	
    	
    	JPanel Toolbar = new JPanel();
    	
    	Toolbar.setPreferredSize(new Dimension(200, 100));
    	
    	Toolbar.setLayout (null);
    	Toolbar.setBorder(new EmptyBorder(15, 10, 20, 10));
    	
    	JButton onestep = new JButton("1 step");
    	onestep.setBounds(0, 10, 90, 25);
    	onestep.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent step1){
    			Main.getThread().runStep(1);
    		}
    	});
    	
    	JButton honderdstep = new JButton("100 steps");
    	honderdstep.setBounds (100, 10, 90, 25);
    	honderdstep.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent step100){
    			Main.getThread().runStep(100);
    		}
    	});
    	
    	
    	JButton start = new JButton("Start");
    	start.setBounds(0, 45, 90, 25);
    	start.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent start){
    			Main.getThread().startThread();
    		}
    	});
    	
    	JButton stop = new JButton("Stop");
    	stop.setBounds (100, 45, 90, 25);
    	stop.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent stop){
    			Main.getThread().stopThread();
    		}
    	});
    	
    	final JTextField text = new JTextField();
    	text.setBounds(0, 80, 90, 25);
    	
    	JButton getText = new JButton("Do steps");
    	getText.setBounds (100, 80, 90, 25);
    	getText.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent doSteps){
    			try
    			{ 
    				/* Het ingevulde nummer proberen op te halen. */
    				int stepsText	= Integer.valueOf (text.getText());
    				
    				Main.getThread().runStep(stepsText);
    			}
    			catch (NumberFormatException e)
    			{
    				/* Er is geen geldig nummer ingevuld, dus laat foutmelding zien. */
    			    JOptionPane.showMessageDialog(new JFrame(), "Invalid amount of steps.\nOnly numbers can be used.", "Invalid amount of steps", JOptionPane.ERROR_MESSAGE);
    			}
    		}
    	});
    	
    	
    	JButton reset = new JButton("Reset");
    	reset.setBounds (0, 115, 90, 25);
    	reset.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent reset){
    			Main.getThread().resetThread();
    		}
    	});
    	
    	
    	
    	JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL, 0, 200, 0);
    	framesPerSecond.setBounds (0, 160, 190, 25);
    	framesPerSecond.addChangeListener(new ChangeListener () {
    		public void stateChanged(ChangeEvent e){
    			JSlider theJSlider = (JSlider) e.getSource();
    			
    			Main.getThread().setThreadSleep (theJSlider.getValue());
    		}
    	});
    	
    	Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
    	
    	labelTable.put (0, new JLabel("0ms"));
    	labelTable.put (100, new JLabel("100ms"));
    	labelTable.put (200, new JLabel("200ms"));
    	
    	framesPerSecond.setLabelTable (labelTable);
    	framesPerSecond.setPaintLabels (true);
    	
    	
    	Toolbar.add(onestep);
    	Toolbar.add(honderdstep);
    	Toolbar.add(start);
    	Toolbar.add(stop);
    	Toolbar.add(text);
    	Toolbar.add(getText);
    	Toolbar.add(reset);
    	Toolbar.add(framesPerSecond);
    	Toolbar.add(Box.createVerticalGlue());
    	
    	this.add(frame, BorderLayout.SOUTH);
    	this.add(menu, BorderLayout.NORTH);
    	
    	frame.add(field, BorderLayout.CENTER);
    	frame.add(Toolbar, BorderLayout.WEST);
    	
        field.add(stepLabel, BorderLayout.NORTH);
        field.add(fieldView, BorderLayout.CENTER);
        field.add(population, BorderLayout.SOUTH);        
        pack();
        setVisible(true);
    }
    
    /**
     * Define a color to be used for a given class of animal.
     * @param animalClass The animal's Class object.
     * @param color The color to be used for the given class.
     */
    public void setColor(Class<?> animalClass, Color color)
    {
        colors.put(animalClass, color);
    }

    /**
     * @return The color to be used for a given class of animal.
     */
    private Color getColor(Class<? extends Object> animalClass)
    {
        Color col = colors.get(animalClass);
        if(col == null) {
            // no color defined for this class
            return UNKNOWN_COLOR;
        }
        else {
            return col;
        }
    }

    /**
     * Show the current status of the field.
     * @param step Which iteration step it is.
     * @param field The field whose status is to be displayed.
     */
    public void showStatus(int step, Field field)
    {
        if(!isVisible()) {
            setVisible(true);
        }
            
        stepLabel.setText(STEP_PREFIX + step);
        stats.reset();
        
        fieldView.preparePaint();

        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if(animal != null) {
                    stats.incrementCount(animal.getClass());
                    fieldView.drawMark(col, row, getColor(animal.getClass()));
                }
                else {
                    fieldView.drawMark(col, row, EMPTY_COLOR);
                }
            }
        }
        stats.countFinished();

        population.setText(POPULATION_PREFIX + stats.getPopulationDetails(field));
        fieldView.repaint();
    }
    
    
    
    public HashMap<Class, DataWrapper> getCurrentData()
    {
		HashMap<Class, Counter> fieldData	= stats.getCounters();
		HashMap<Class, DataWrapper> newData = new HashMap<Class, DataWrapper>();
		
		
			for (Class c: fieldData.keySet ())
			{
				HashMap<Counter, Color> temp = new HashMap<Counter, Color>();
				
				temp.put (fieldData.get(c), colors.get(c));
				
				
				newData.put (c, new DataWrapper (fieldData.get(c), colors.get(c)));
			}
		
		return newData;
    }
    
    

    /**
     * Determine whether the simulation should continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
        return stats.isViable(field);
    }
    
    /**
     * Provide a graphical view of a rectangular field. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the field.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
    private class FieldView extends JPanel
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private final int GRID_VIEW_SCALING_FACTOR = 6;

        private int gridWidth, gridHeight;
        private int xScale, yScale;
        Dimension size;
        private Graphics g;
        private Image fieldImage;

        /**
         * Create a new FieldView component.
         */
        public FieldView(int height, int width)
        {
            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);
        }

        /**
         * Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
                                 gridHeight * GRID_VIEW_SCALING_FACTOR);
        }

        /**
         * Prepare for a new round of painting. Since the component
         * may be resized, compute the scaling factor again.
         */
        public void preparePaint()
        {
            if(! size.equals(getSize())) {  // if the size has changed...
                size = getSize();
                fieldImage = fieldView.createImage(size.width, size.height);
                g = fieldImage.getGraphics();

                xScale = size.width / gridWidth;
                if(xScale < 1) {
                    xScale = GRID_VIEW_SCALING_FACTOR;
                }
                yScale = size.height / gridHeight;
                if(yScale < 1) {
                    yScale = GRID_VIEW_SCALING_FACTOR;
                }
            }
        }
        
        /**
         * Paint on grid location on this field in a given color.
         */
        public void drawMark(int x, int y, Color color)
        {
            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, xScale-1, yScale-1);
        }

        /**
         * The field view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g)
        {
            if(fieldImage != null) {
                Dimension currentSize = getSize();
                if(size.equals(currentSize)) {
                    g.drawImage(fieldImage, 0, 0, null);
                }
                else {
                    // Rescale the previous image.
                    g.drawImage(fieldImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }
}
