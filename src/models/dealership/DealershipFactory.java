package models.dealership;

public class DealershipFactory
{
    public Dealership get_dealership(String brand, int stock)
    {
        if (brand.equalsIgnoreCase("Mercedes"))
        {
            return new MercedesDealership("Mercedes", stock);
        }

        if (brand.equalsIgnoreCase("Porsche"))
        {
            return new PorscheDealership("Porsche", stock);
        }

        if (brand.equalsIgnoreCase("BMW"))
        {
            return new BmwDealership("BMW", stock);

        } else {
            throw new IllegalArgumentException("unknown dealership : " + brand);
        }
    }
}
