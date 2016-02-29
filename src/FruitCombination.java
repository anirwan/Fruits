import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anirwan on 17/02/2016.
 */
public class FruitCombination {
    private List<Map> combinations = new ArrayList<Map>();

    public void setCombinations(Map<String, String> combination) {
        Map<String, String> tmpMap = new HashMap<String, String>();
        tmpMap.putAll(combination);
        this.combinations.add(tmpMap);
    }

    public List<Map> getCombinations() {
        return this.combinations;
    }
}
