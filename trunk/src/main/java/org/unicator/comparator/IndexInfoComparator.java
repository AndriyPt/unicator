package org.unicator.comparator;

import java.util.Comparator;

import org.unicator.model.IndexInfo;

public class IndexInfoComparator implements Comparator<IndexInfo>  {

    public int compare(final IndexInfo o1, final IndexInfo o2) {
        
        if (null == o1) {
            if (null == o2) {
                return 0;
            }
            return -1;
        }
        
        int result = o1.compareTo(o2);
        return result;
    }
}
