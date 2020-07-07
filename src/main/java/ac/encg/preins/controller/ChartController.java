package ac.encg.preins.controller;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Aissam
 */
@Controller
@Getter
@Setter
@ViewScoped
@Named("chartController")
public class ChartController implements Serializable {

    private PieChartModel pieModel1;

  private void createPieModel1() {
        pieModel1 = new PieChartModel();
        
        pieModel1.set("Brand 1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);
        pieModel1.set("Brand 4", 421);
        
        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("w");
    }

}
