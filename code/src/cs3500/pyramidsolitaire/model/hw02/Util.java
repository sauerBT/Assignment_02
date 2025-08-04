package cs3500.pyramidsolitaire.model.hw02;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Util {

    /**
     * Produce a clone of the given collection.
     * @param coll The collection to be cloned.
     * @return The clone of the given collection.
     * @param <K> The type of element within the collection.
     */
    public static <K> List<K> clone(List<K> coll) {
        List<K> result = new ArrayList<>();
        for (K e : coll) {
            result.add(e);
        }
        return result;
    }
    /**
     * Produce the first x elements of a given List, with x being a given
     * @param coll The List of elements
     * @param numOfElements The number of x elements to get from the list
     * @return the first x elements of the given list
     * @param <K> The type of element the List uses
     */
    public static <K> List<K> getFirstX(List<K> coll, int numOfElements) {
        return getFirstXHelper(Util.clone(coll), numOfElements, new ArrayList<>());
    }

    private static <K> List<K> getFirstXHelper(List<K> wl, int numOfElements, List<K> listAcc) {
        if (numOfElements == 0) {
            return listAcc;
        } else {
            listAcc.add(wl.getFirst());
            wl.removeFirst();
            return getFirstXHelper(wl, numOfElements - 1, listAcc);
        }
    }
    /**
     * Produce a List with the first x elements removed, with x being a given
     * @param coll The List of elements
     * @param numOfElements The number of x elements to remove from the list
     * @return The list with x elements removed
     * @param <K> The type of element the List uses
     */
    public static <K> List<K> removeFirstX(List<K> coll, int numOfElements) {
        return removeFirstXHelper(Util.clone(coll), numOfElements);
    }

    private static <K> List<K> removeFirstXHelper(List<K> listAcc, int numOfElements) {
        if (numOfElements == 0) {
            return listAcc;
        } else {
            listAcc.removeFirst();
            return removeFirstXHelper(listAcc, numOfElements - 1);
        }
    }

    public static <K> boolean containsDuplicatesOf(IPred2<K> pred2, List<K> coll, K obj) { return Util.count(pred2, coll, obj) > 1; }

    public static <K> int count(IPred2<K> pred2, List<K> coll, K obj) { return Util.countHelper(pred2, coll, obj, 0); }

    private static <K> int countHelper(IPred2<K> pred2, List<K> coll, K obj, int countAcc) {
        if (coll.isEmpty()) {
            return countAcc;
        } else {
            if (pred2.apply(coll.getFirst(), obj)) {
                return countHelper(pred2, coll.subList(1, coll.size()), obj, countAcc += 1);
            } else {
                return countHelper(pred2, coll.subList(1, coll.size()), obj, countAcc);
            }
        }
    }

    public static <K> List<K> add(List<K> coll, K element) {
        List<K> result = Util.copy(coll);
        result.add(element);
        return result;
    }

    public static <K> List<K> append(List<K> coll01, List<K> coll02) {
        List<K> result = Util.copy(coll01);
        result.addAll(coll02);
        return result;
    }
    // TODO
    /**
     * Produce True if the given collection meets the given conditions with respect to the given object.
     *
     * @param pred2 The predicate or conditions.
     * @param coll The given collection.
     * @param obj The given object.
     * @return True if conditions are met, otherwise false.
     * @param <K> The data type of the given collection's elements and the given object.
     */
    public static <K> boolean contains(IPred2<K> pred2, List<K> coll, K obj) {
        if (coll.isEmpty()) {
            return false;
        } else {
            if (pred2.apply(coll.getFirst(), obj)) {
                return true;
            } else {
                return Util.contains(pred2, coll.subList(1, coll.size()), obj);
            }
        }
    }

    /**
     * Produce a copy of the given collection.
     *
     * @param coll This is the given collection to be copied.
     * @return The copied collection
     * @param <K> The data object the collection holds
     */
    public static <K> List<K> copy(List<K> coll) { return copyHelper(coll, new ArrayList<>()); }

    private static <K> List<K> copyHelper(List<K> coll, List<K> acc) {
        if (coll.isEmpty()) {
            return acc;
        } else {
            acc.add(coll.getFirst());
            return copyHelper(coll.subList(1, coll.size()), acc);
        }
    }

    /**
     * Abstract function to produce an element from a collection based on a given predicate.
     *
     * @param pred2 The first class function defining the conditions for matching an element.
     * @param coll The given collection
     * @param c A given element to compare individual elements from a collection to.
     * @return The first element from a collection to match the conditions of the given predicate.
     * @param <K> The data type of the element.
     */
    public static <K> Optional<K> findOne(IPred2<K> pred2, List<K> coll, K c) {
        for (K j : coll) {
            if (pred2.apply(j, c)) { return Optional.of(j); }
        }
        return Optional.empty();
    }

    // TODO
    /**
     * Produce a new List that contains only elements from the original list that meet the conditions defined by the given
     * predicate.  A List is given for comparison elements. The predicate comparison is compares every element of the
     * given collection to every element of the comparison collection.
     *
     * @param pred2 The first class function for providing conditions.
     * @param coll The given collection.
     * @param compColl The list of elements to compare.
     * @return A list of elements including only those from the original given list that pass the predicate conditions.
     * @param <K> The data type of the elements.
     */
    public static <K> List<K> findIfExclude(IPred2<K> pred2, List<K> coll, List<K> compColl) {
        return findIfExcludeHelper(pred2, coll, compColl, new ArrayList<>());
    }

    private static <K> List<K> findIfExcludeHelper(IPred2<K> pred2, List<K> coll, List<K> compColl, List<K> acc) {
        if (coll.isEmpty()) {
            return acc;
        } else if (compColl.isEmpty()) {
            return coll;
        } else {
            if (!Util.contains(pred2, compColl, coll.getFirst())) {
                acc.add(coll.getFirst());
            }
            return findIfExcludeHelper(pred2, coll.subList(1, coll.size()), compColl, acc);
        }
    }
}
