import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class VehicleCard implements Comparable<VehicleCard>{
	public enum Category{ // begin of Category-Enum
	ECONOMY_MPG("Miles/Gallon"),
	CYLINDERS_CNT("Zylinder"),
	DISPLACEMENT_CCM("Hubraum[cc]"),
	POWER_HP("Leistung[hp]"),
	WEIGHT_LBS("Gewicht[lbs]"){
		@Override
		 public boolean isInverted(){
			return true;
		}
	},
	ACCELERATION("Beschleunigung"){
		@Override
		 public boolean isInverted(){
			return true;
		}
	},
	YEAR("Baujahr[19xx]");
		
	public final String categoryName;
	
	private Category(final String categoryName) {
		if(categoryName.isEmpty() || categoryName == null) {
			throw new IllegalArgumentException("");
		 }
		
		this.categoryName = categoryName;
	}
	
	public boolean isInverted() {
		return false;
	}
	
	public int bonus(final Double value) {
		if(isInverted()) {
			return -value.intValue();
		}
		
		return value.intValue();
	}
	
	@Override
	public String toString() {
		return categoryName;
	}
	
} // end of Category-Enum
	
//instance variables	
	private String name;
	private Map<Category, Double> categories;
	
//constructor
	public VehicleCard(final String name, final Map<Category, Double> categories) {
		if( name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.name = name;
		
		if(categories == null ||categories.isEmpty()) {
			throw new IllegalArgumentException("Categories is empty.");
		}
		
		for(Category c : Category.values()) {
			if(!categories.containsKey(c)) {
				throw new IllegalArgumentException("Categories does not contain all required values.");
			}
		}
		
		for(Map.Entry<Category, Double> entry : categories.entrySet()) {
			if(entry.getValue() == null || entry.getValue() < 0) {
				throw new IllegalArgumentException("Map Value is not valid.");
			}
		}
		
		Map<Category, Double> newMap = new HashMap<Category, Double>(categories);
		this.categories = newMap;
	}

//getters
	public String getName() {
		return this.name;
	}
	
	public Map<Category, Double> getCategories() {
		Map<Category, Double> newShallowCopy = new HashMap<Category, Double>(this.categories);
		return newShallowCopy;
	}
	
//methods
	public static Map<Category, Double> newMap(double economy, double cylinders, double displacement, double power, double weight, double acceleration, double year) {
		Map<Category, Double> new_map = new HashMap<>();
		new_map.put(Category.ECONOMY_MPG, economy);
		new_map.put(Category.CYLINDERS_CNT, cylinders);
		new_map.put(Category.DISPLACEMENT_CCM, displacement);
		new_map.put(Category.POWER_HP, power);
		new_map.put(Category.WEIGHT_LBS, weight);
		new_map.put(Category.ACCELERATION, acceleration);
		new_map.put(Category.YEAR, year);
		
		return new_map;
	}
	
	@Override 
	public int compareTo(final VehicleCard other) { // by name AND total bonus
		int ret_comp = this.name.compareTo(other.name);
		
		if(ret_comp == 0) {
			Integer first_ = this.totalBonus();
			Integer second_ = other.totalBonus();
			ret_comp = first_.compareTo(second_);
		}
		
		return ret_comp;
	}
		
	public int totalBonus() {
		int sum = 0;
		for(Map.Entry<Category, Double> inst : categories.entrySet()) {
			sum += inst.getKey().bonus(inst.getValue());
		}
		
		return sum;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(name, this.totalBonus());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleCard other = (VehicleCard) obj;
		return Objects.equals(this.totalBonus(), other.totalBonus()) && Objects.equals(name, other.name);
	}
	

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append("- ").append(this.getName()).append("(").append(this.totalBonus()).append(") -> {");
		boolean first = true;
		for(Map.Entry<Category, Double> m_e : categories.entrySet()) {
			if(first) {
				first = false;
			}
			else {
				out.append(", ");
			}
			
			out.append(m_e.getKey().toString()).append("=").append(m_e.getValue().toString());
		}
		out.append("}");
		return out.toString();
	}
	
}







/*

public static VehicleCard[] getBestNCards(Collection<VehicleCard> cards, int n) {
		if(cards == null || cards.isEmpty() || n < 1)
			throw new IllegalArgumentException("Error");
		if(cards.size() < n) {
			VehicleCard[] ret = cards.toArray(new VehicleCard[0]);
			Arrays.sort(ret,(o1,o2) -> (o2.totalBonus() - o1.totalBonus()));
			return ret;
		}else {
			VehicleCard[] ret = cards.toArray(new VehicleCard[0]);
			Arrays.sort(ret,(o1,o2) -> (o2.totalBonus() - o1.totalBonus()));
			VehicleCard[] res = Arrays.copyOf(ret, n);
			return res;
				
			}
			
		}
	
	public static VehicleCard[] getWorstNCards(Collection<VehicleCard> cards, int n) {
		if(cards == null || cards.isEmpty() || n < 1)
			throw new IllegalArgumentException("Error");
		if(n > cards.size()) {
			VehicleCard[] res = cards.toArray(new VehicleCard[0]);
			Arrays.sort(res,(o1,o2)->(o1.totalBonus() - o2.totalBonus()));
			return res;
		}
		
		else{
			VehicleCard[] copy = cards.toArray(new VehicleCard[0]);
			Arrays.sort(copy,(o1,o2)->(o1.totalBonus() - o2.totalBonus()));
			VehicleCard[] res = Arrays.copyOf(copy, n);
			return res;
		}
		
		
	}
	
	
	
	
	public static VehicleCard[] getCardsBelow(Collection<VehicleCard> cards, double bonusThreshold) {
		if(cards == null || cards.isEmpty())
			throw new IllegalArgumentException("Error");
		Collection<VehicleCard> copy = new ArrayList<VehicleCard>();
		for(VehicleCard card : cards) {
			if(card.totalBonus() < bonusThreshold)
				copy.add(card);
		}
		VehicleCard[] res = copy.toArray(new VehicleCard[0]);
		Arrays.sort(res,(o1,o2)->(o1.totalBonus()-o2.totalBonus()));
		return res;
		
	}
	
	double getPrice() {
		double res = 0.;
		double c = categories.get(Category.ECONOMY_MPG);
		res = name.length() * c * 10;
		return res;
	}
	
	
	
	public static VehicleCard[] getCardsAbove(Collection<VehicleCard> cards, double bonusThreshold) {
		if(cards == null || cards.isEmpty())
			throw new IllegalArgumentException("Error");
		Collection<VehicleCard> copy = new ArrayList<VehicleCard>();
		for(VehicleCard card : cards) {
			if(card.totalBonus() > bonusThreshold)
				copy.add(card);
		}
		
		VehicleCard[] res = copy.toArray(new VehicleCard[0]);
		Arrays.sort(res,(o1,o2)->(o1.totalBonus() - o2.totalBonus()));
		
		return res;
		
	}
	
	double getEcoScore() {
		double res = 0.;
		
		res = categories.get(Category.ECONOMY_MPG) * categories.get(Category.CYLINDERS_CNT);
		
		return res;
	}





*/
