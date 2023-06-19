package pluginCurrency;

import org.example.program.containers.Manager;
import org.example.program.entities.commodities.Commodity;
import org.example.program.plugin.Plugin;
import org.example.program.topbar.TopContainer;

public class PluginCurrency implements Plugin {

    @Override
    public void setup(TopContainer topContainer, Manager manager) {
        Manager m = Manager.getInstance();
        Converter conv = Converter.getInstance();
        conv.setCurrentCurrency("USD");
        for (Commodity c : m.getInventoryContainer().getBuffer()){
            m.getInventoryContainer().setProductById(c.getId(), decorateCommodity(c));
//            System.out.println(c.getName() + "    " +c.getCurrency()+" "+ c.getPrice());
        }


    }

    public Commodity decorateCommodity(Commodity c){
        CommodityPlugin cp = new CommodityPlugin(c);
        return cp;
    }

    @Override
    public void postSetup() {

    }

    public void disconnect(){

    }

    public void changeCurrency(String c){

    }

}
