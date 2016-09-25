package entities;


import java.util.Comparator;

public class ItemsRecommendedCount implements Comparator<ItemsRecommendedCount>{
    private Integer id;
    private Integer count;

    public ItemsRecommendedCount(Integer id, Integer count) {
        this.id = id;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public static final Comparator<ItemsRecommendedCount> DESC_COMPARATOR = new Comparator<ItemsRecommendedCount>() {
        public int compare(ItemsRecommendedCount o1, ItemsRecommendedCount o2) {
            return o2.getCount() - o1.getCount();
        }
    };

    @Override
    public int compare(ItemsRecommendedCount o1, ItemsRecommendedCount o2) {
        return o2.getCount() - o1.getCount();
    }
}
