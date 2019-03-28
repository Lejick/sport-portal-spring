package org.portal.model.leagues;

import org.portal.backend.data.model.League;
import com.vaadin.flow.component.grid.Grid;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
public class LeaguesGrid extends Grid<League> {

    public LeaguesGrid() {
       /* LineGrabber lineTennisGrabber = GrabberFactory.getTennisLineGrabber();
        MaxBetGrabber maxGrabber = GrabberFactory.getTennisMaxBetGrabber();

        if(!lineTennisGrabber.isRunning()) {
            Thread thread = new Thread(lineTennisGrabber);
            thread.run();
        }

        if(!maxGrabber.isRunning()) {
            Thread thread = new Thread(maxGrabber);
            thread.run();
        }
*/

     //   new Thread(() -> GrabberFactory.getTennisMaxBetGrabber().run()).start();

        setSizeFull();
        addColumn(League::getName)
                .setHeader("Name")
                .setFlexGrow(20)
                .setSortable(true);

        addColumn(League::getStartsFormat)
                .setHeader("Start Date(MSK)")
                .setFlexGrow(20)
                .setSortable(true);

    }

}
