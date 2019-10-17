package com.epam.jdi.light.elements.complex.table;

import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.light.elements.SeleniumWebList;
import com.epam.jdi.light.elements.base.JDIBase;
import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.complex.IList;
import com.epam.jdi.light.elements.interfaces.base.IBaseElement;
import com.epam.jdi.tools.LinqUtils;
import com.epam.jdi.tools.PrintUtils;
import com.epam.jdi.tools.func.JFunc;
import com.epam.jdi.tools.map.MapArray;
import com.epam.jdi.tools.pairs.Pair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.epam.jdi.light.common.Exceptions.exception;
import static com.epam.jdi.light.common.UIUtils.create;
import static com.epam.jdi.light.elements.pageobjects.annotations.WebAnnotationsUtil.getElementName;
import static com.epam.jdi.light.logger.LogLevels.DEBUG;
import static com.epam.jdi.tools.StringUtils.namesEqual;

public class Line implements IList<String>, IBaseElement {
    private JFunc<MapArray<String, String>> dataMap;
    private SeleniumWebList elements;
    private List<String> headers;
    public JDIBase base() {
        return elements.base();
    }

    public Line() {}
    public Line(SeleniumWebList elements, List<String> headers) {
        this.elements = elements;
        this.headers = headers;
        List<String> values = LinqUtils.map(elements, UIElement::getText);
        this.dataMap = () -> new MapArray<>(headers, values);
    }
    public static Line initLine(List<String> list, List<String> headers) {
        Line line = new Line();
        line.list = new ArrayList<>(list);
        line.headers = new ArrayList<>(headers);
        return line;
    }
    private MapArray<String, String> data;
    private List<String> list;
    private List<String> getList(int minAmount) {
        return list != null && list.size() >= minAmount
                ? list
                : getData(minAmount).values();
    }
    // TODO Implement
    public String get(String value) {return ""; }
    private MapArray<String, String> getData(int minAmount) {
        if (data == null || data.size() < minAmount)
            data = dataMap.execute();
        return data;
    }

    /**
     * Create list of specified size
     * @param minAmount
     * @return List
     */
    @JDIAction(level = DEBUG)
    public MapArray<String, String> elements(int minAmount) {
        return getData(minAmount);
    }

    public String getValue() {
        return PrintUtils.print(getList(0), ";");
    }
    public String print() { return getValue(); }

    public void clear() { data = null; }

    public <D> D asData(Class<D> data) {
        D instance;
        try { instance = create(data); }
        catch (Exception ex) { throw exception("Can't convert row to Data (%s)", data.getSimpleName()); }
        int i = 0;
        for (Field field : data.getDeclaredFields()) {
            try {
                field.set(instance, getList(i).get(i));
            } catch (Exception ex) {
                throw exception("Can't set table value '%s' to field '%s'", getData(i).get(i), field.getName());
            }
            i++;
        }
        return instance;
    }
    public <D> D asData(Class<D> data, MapArray<String, String> line) {
        D instance;
        try { instance = create(data); }
        catch (Exception ex) { throw exception("Can't convert row to Entity (%s)", data.getSimpleName()); }
        for (Pair<String, String> cell : line) {
            Field field = LinqUtils.first(instance.getClass().getDeclaredFields(),
                f -> namesEqual(getElementName(f), cell.key));
            if (field != null)
                try {
                    field.set(instance, cell.value);
                } catch (Exception ex) {
                    throw exception("Can't set table entity to field '%s'", field.getName());
                }
        }
        return instance;
    }

    public <T> T asLine(Class<T> cl) {
        T instance;
        try { instance = create(cl); }
        catch (Exception ex) { throw exception("Can't convert row to Entity (%s)", cl.getSimpleName()); }
        for (int i = 0; i < headers.size(); i++) {
            String header = headers.get(i);
            Field field = LinqUtils.first(instance.getClass().getDeclaredFields(),
                f -> namesEqual(getElementName(f), header));
            try {
                IBaseElement ui = ((IBaseElement)field.get(instance));
                ui.base().setWebElement(elements.get(i));
            } catch (Exception ex) {
                throw exception("Can't set table entity to field '%s'", field.getName());
            }
        }
        return instance;
    }

    @Override
    public String toString() {
        return print();
    }
}
