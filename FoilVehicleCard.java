package a12044041;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class FoilVehicleCard extends VehicleCard {
	private Set<Category> specials;

	public FoilVehicleCard(final String name, final Map<Category, Double> categories, final Set<Category> specials) {
		super(name, categories);

		if (specials == null || specials.isEmpty() || specials.size() > 3) {
			throw new IllegalArgumentException("Specials is null/empt/more than 3 items.");
		}
		
		for(Category c : specials) {
			if(c == null)
				throw new IllegalArgumentException("null in specials");
		}
			

		Set<Category> specCatShallow = new TreeSet<Category>(specials);
		this.specials = specCatShallow;
	}

	public Set<Category> getSpecials() {
		Set<Category> reSpecShall = new TreeSet<Category>(specials);
		return reSpecShall;
	}

	@Override
	public int totalBonus() {
		int sum = super.totalBonus();
		int something = 0;
		for (Map.Entry<Category, Double> m : getCategories().entrySet()) {
			for (Category c : specials) {
				if (   m.getKey().equals(c)  )
					something = something + Math.abs(m.getKey().bonus(m.getValue()));
			}
		}
		return sum + something ;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("- ").append(getName()).append("(").append(totalBonus()).append(") -> {");
		boolean first = true;
		for (Map.Entry<Category, Double> m : getCategories().entrySet()) {
			if (first)
				first = false;
			else {
				sb.append(", ");
			}
			
			if(specials.contains(m.getKey())) {
				sb.append("*").append(m.getKey().toString()).append("*").append("=").append(m.getValue().toString());
			}
			else {
			 sb.append(m.getKey().toString()).append("=").append(m.getValue().toString());
			}
			
		}
		sb.append("}");
		return sb.toString();
	}

}
