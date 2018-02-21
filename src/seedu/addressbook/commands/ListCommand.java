package seedu.addressbook.commands;

import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list with index numbers in alphabetical order.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Lists the entries in the addressbook in alphabetical order according to their name.
     */
    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        List<ReadOnlyPerson> listOfSortedPersons = orderListOfEntries(allPersons);
        return new CommandResult(getMessageForPersonListShownSummary(listOfSortedPersons), listOfSortedPersons);
    }

    /**
     * Receives an unordered list of entries in an addressbook and orders them in alphabetical order
     */

    public List<ReadOnlyPerson> orderListOfEntries(List<ReadOnlyPerson> unorderedListOfPersons)
    {
        List<ReadOnlyPerson> listOfSortedPersons = new ArrayList<>();
        for (int i = 0; i < unorderedListOfPersons.size(); i++) { // Copy List from unsorted array to array that will be sorted
            listOfSortedPersons.add(unorderedListOfPersons.get(i));
        }

        for (int i = 0; i < listOfSortedPersons.size()-1; i++)
            for (int j = 0; j < listOfSortedPersons.size()-i-1; j++)
                if (listOfSortedPersons.get(j).getName().toString().toLowerCase().compareTo(listOfSortedPersons.get(j+1).getName()
                        .toString().toLowerCase()) > 0)
                {
                    ReadOnlyPerson temporary = listOfSortedPersons.get(j);
                    listOfSortedPersons.set(j,listOfSortedPersons.get(j+1));
                    listOfSortedPersons.set(j+1,temporary);
                }
        return listOfSortedPersons;
    }
}
