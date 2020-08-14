package car_builder;

public class MyBuild
{
    private String model;
    private double basePrice;
    private boolean pkgA;
    private boolean pkgB;
    private boolean metallic;
    private String color;

    protected final double S40_BASE_PRICE = 27700;
    protected final double S60_BASE_PRICE = 32500;
    protected final double S70_BASE_PRICE = 36000;
    protected final double S80_BASE_PRICE = 44000;

    private final double PACKAGE_A = 2200;
    private final double PACKAGE_B = 3250;
    private final double METALLIC_PAINT = 650;

    /**
     * No-arg constructor
     * This constructor initializes all String fields to "Uknown",
     * all numerical fields to 0 / 0.0,
     * and all boolean fields to false.
     */
    public MyBuild()
    {
        model = "Unknown";
        basePrice = 0.0;
        pkgA = false;
        pkgB = false;
        metallic = false;
        color = "Unknown";
    }

    /**
     * This method returns the selected model.
     * @return String containing the model
     */
    public String getModel()
    {
        return model;
    }

    /**
     * This method returns the base price of the selected model.
     * @return double base price
     */
    public double getBasePrice()
    {
        return basePrice;
    }

    /**
     * This method returns whether package A is selected.
     * @return boolean reflecting whether the option has been selected
     */
    public boolean getPkgA()
    {
        return pkgA;
    }

    /**
     * This method returns whether package B is selected.
     * @return boolean reflecting whether the option has been selected
     */
    public boolean getPkgB()
    {
        return pkgB;
    }

    /**
     * This method returns the price of package A.
     * @return double price of package A
     */
    public double getPkgAPrice()
    {
        return PACKAGE_A;
    }

    /**
     * This method returns the price of package B.
     * @return double price of package B
     */
    public double getPkgBPrice()
    {
        return PACKAGE_B;
    }

    /**
     * This method returns the price of metallic paint.
     * @return double price of metallic paint
     */
    public double getMetallicPaintPrice()
    {
        return METALLIC_PAINT;
    }

    /**
     * This method returns whether metallic paint has been selected.
     * @return boolean reflecting whether the option has been selected
     */
    public boolean getMetallic()
    {
        return metallic;
    }

    /**
     * This method returns the color.
     * @return String containing the color
     */
    public String getColor()
    {
        return color;
    }

    /**
     * This method sets the selected model and initializes the basePrice field
     * based on predetermined constants.
     * @param model String containing the model name
     */
    protected void setModel(String model)
    {
        this.model = model;
        if (this.model.equals("S40"))
        {
            basePrice = S40_BASE_PRICE;
        }
        else if (this.model.equals("S60"))
        {
            basePrice = S60_BASE_PRICE;
        }
        else if (this.model.equals("S70"))
        {
            basePrice = S70_BASE_PRICE;
        }
        else
        {
            basePrice = S80_BASE_PRICE;
        }
    }

    /**
     * This method sets whether customer has chosen package A.
     * @param wants boolean reflecting whether option is selected
     */
    protected void setPkgA(boolean wants)
    {
        pkgA = wants;
    }

    /**
     * This method sets whether customer has chosen package B.
     * @param wants boolean reflecting whether option is selected
     */
    protected void setPkgB(boolean wants)
    {
        pkgB = wants;
    }

    /**
     * This method sets whether customer has chosen metallic paint.
     * @param wants boolean reflecting whether option is selected
     */
    protected void setMetallic(boolean wants)
    {
        metallic = wants;
    }

    /**
     * This method sets the color.
     * @param color String containing the color
     */
    protected void setColor(String color)
    {
        this.color = color;
    }

    /**
     * This method returns the total price including the cost of any selected options.
     * @return double price
     */
    protected double getTotalPrice()
    {
        double price = basePrice;
        if (pkgA)
        {
            price += PACKAGE_A;
        }
        if (pkgB)
        {
            price += PACKAGE_B;
        }
        if (metallic)
        {
            price += METALLIC_PAINT;
        }
        return price;
    }

    /**
     * toString method
     * @return String containing information about the object
     */
    @Override
    public String toString()
    {
        String str = String.format("Model:\t\t\t%s"+
                        "%nBase price:\t\t$%,.2f"+
                        "%nPackage A selected:\t%b"+
                        "%nPackage B selected:\t%b"+
                        "%nMetallic paint:\t\t%b"+
                        "%nPrice as built:\t\t$%,.2f",
                model, basePrice, pkgA, pkgB, metallic, this.getTotalPrice());
        return str;
    }
}

