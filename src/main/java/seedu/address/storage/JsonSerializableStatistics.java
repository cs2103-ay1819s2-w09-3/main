package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.statistics.Bill;
import seedu.address.model.statistics.ReadOnlyStatistics;
import seedu.address.model.statistics.Statistics;

/**
 * An Immutable RestOrRant that is serializable to JSON format.
 */
@JsonRootName(value = "statsList")
class JsonSerializableStatistics {

    private final List<JsonAdaptedBill> statsList = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableRestOrRant} with the given persons.
     */
    @JsonCreator
    public JsonSerializableStatistics(@JsonProperty("statsList") List<JsonAdaptedBill> statsList) {
        this.statsList.addAll(statsList);
    }

    /**
     * Converts a given {@code ReadOnlyRestOrRant} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRestOrRant}.
     */
    public JsonSerializableStatistics(ReadOnlyStatistics source) {
        statsList.addAll(source.getBillList().stream().map(JsonAdaptedBill::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code RestOrRant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Statistics toModelType() throws IllegalValueException {
        Statistics statistics = new Statistics();
        for (JsonAdaptedBill jsonAdaptedBill : statsList) {
            Bill bill = jsonAdaptedBill.toModelType();
            statistics.addBill(bill);
        }
        return statistics;
    }

}
