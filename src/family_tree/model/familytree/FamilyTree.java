package family_tree.model.familytree;

import family_tree.model.familytree.iterator.PersonIterator;
import family_tree.model.person.*;
import family_tree.model.person.comparator.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FamilyTree<T extends FamilyTreeItem<T>> implements Serializable, Iterable<T> {
    private List<T> allPeople;
    private T peakMother;
    private T peakFather;

    // constructors
    public FamilyTree() {
        allPeople = new ArrayList<>();
    }

    public FamilyTree(T person) {
        allPeople = new ArrayList<>();
        allPeople.add(person);
    }

    public boolean addPerson(T person) {
        if (allPeople == null) {
            allPeople = new ArrayList<>();
        }
        allPeople.add(person);
        return allPeople.contains(person);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T person:this) {
            sb.append(String.format("%s \n", person));
        }
        return sb.substring(0,sb.length()-3);
    }

    public Boolean isEmpty() {
        return (peakFather == null) && (peakMother == null) && allPeople.isEmpty();
    }

    public String printShort() {
        int index = 1;
        StringBuilder sb = new StringBuilder();
        for (T person:this) {
            sb.append(String.format("%s. ", index++));
            sb.append(person.getName());
            sb.append(" ");
            sb.append(person.getBirthDate());
            sb.append(" ");
            sb.append(person.getAge());
            sb.append(" л.(г.)");
            sb.append("\n");
        }
        return sb.toString().substring(0, sb.length()-1);
    }

    public void setPeak(String name) {
        for (T person : this) {
            if (person.getName().equalsIgnoreCase(name))
                if (person.getGender() == Gender.Female) {
                    this.peakMother = person;
                } else {
                    this.peakFather = person;
                }
        }
    }

    public T getPeakMother() {
        return peakMother;
    }

    public T getPeakFather() {
        return peakFather;
    }

    public T getPersonByName(String name) {
        for (T personAtList:this) {
            if (personAtList.getName().equalsIgnoreCase(name)){
                return personAtList;
            }
        }
        return null;
    }

    public T getPersonByIndex(int index) {
        if (index < this.allPeople.size()) {
            return this.allPeople.get(index);
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new PersonIterator<>(this.allPeople);
    }

    public void sortByName() {
        Collections.sort(this.allPeople, new PersonComparatorByName<>());
    }

    public void sortByNameReverse() {
        Collections.sort(this.allPeople, new PersonComparatorByNameReverse<>());
    }

    public void sortByAge() {
        Collections.sort(this.allPeople,new PersonComparatorByAge<>());
    }

    public void sortByAgeReverse() {
        Collections.sort(this.allPeople,new PersonComparatorByAgeReverse<>());
    }

    public void sortByBirth() {
        Collections.sort(this.allPeople,new PersonComparatorByBirth<>());
    }

    public void sortByBirthReverse() {
        Collections.sort(this.allPeople,new PersonComparatorByBirthReverse<>());
    }

    public void sortByChildren() {
        Collections.sort(this.allPeople,new PersonComparatorByChildren<>());
    }

    public void sortByChildrenReverse() {
        Collections.sort(this.allPeople,new PersonComparatorByChildrenReverse<>());
    }

    public boolean delByName(String fullName) {
        T person = this.getPersonByName(fullName);
        return this.allPeople.remove(person);
    }


    public int getSize() {
        return this.allPeople.size();
    }

    public List<T> getItemsList() {
        return this.allPeople;
    }

}
