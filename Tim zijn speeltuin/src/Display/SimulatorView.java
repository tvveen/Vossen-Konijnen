package Display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Simulator.RunThread;

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
    
    private RunThread run;

    /**
     * Create a view of the given width and height.
     * @param height The simulation's height.
     * @param width  The simulation's width.
     */
    public SimulatorView(int height, int width)
    {
        stats = new FieldStats();
        run = new RunThread();
        colors = new LinkedHashMap<Class, Color>();

        setTitle("Fox and Rabbit Simulation");
        stepLabel = new JLabel(STEP_PREFIX, JLabel.CENTER);
        population = new JLabel(POPULATION_PREFIX, JLabel.CENTER);
        
        setLocation(100, 50);
   		
        JPanel frame = new JPanel();
        frame.setLayout(new BorderLayout());
        frame.setBorder(new EmptyBorder(10, 10, 10, 10));
        
    	fieldView = new FieldView(height, width);
    	
    	JMenuBar menu = new JMenuBar();
    	menu.setLayout(new BorderLayout());
    	menu.setBorder(new EmptyBorder(10, 10, 5, 5));
    	
    	JMenu menu1 = new JMenu("Menu 1");
    	
    	JMenu menu2 = new JMenu("Menu 2");
    	
    	JMenu help = new JMenu("Help");
    	
    	JPanel field = new JPanel();
    	field.setLayout(new BorderLayout());
    	
    	JPanel Toolbar = new JPanel();
    	Toolbar.setLayout(new GridLayout(10, 0));
    	Toolbar.setBorder(new EmptyBorder(20, 10, 20, 10));
    	
    	JButton onestep = new JButton("1 step");
    	onestep.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent step1){
    			run.runStep(1);
    		}
    	});
    	
    	JButton honderdstep = new JButton("100 steps");
    	honderdstep.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent step100){
    			run.runStep(100);
    		}
    	});
    	
    	JButton start = new JButton("Start");
    	start.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent start){
    			run.startThread();
    		}
    	});
    	
    	JButton stop = new JButton("Stop");
    	stop.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent stop){
    			run.stopThread();
    		}
    	});
    	
    	final JTextField text = new JTextField();
    	
    	JButton getText = new JButton("Do steps");
    	getText.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent doSteps){
    			int stepsText = Integer.parseInt(text.getText());
    			run.runStep(stepsText);
    		}
    	});
    	
    	JButton reset = new JButton("Reset");
    	reset.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent reset){
    			run.resetThread();
    		}
    	});
    	
    	menu.add(menu1);
    	menu.add(menu2);
    	menu.add(help);
    	
    	Toolbar.add(onestep);
    	Toolbar.add(honderdstep);
    	Toolbar.add(start);
    	Toolbar.add(stop);
    	Toolbar.add(text);
    	Toolbar.add(getText);
    	Toolbar.add(reset);
    	
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
    public void setColor(Class animalClass, Color color)
    {
        colors.put(animalClass, color);
    }

    /**
     * @return The color to be used for a given class of animal.
     */
    private Color getColor(Class animalClass)
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
