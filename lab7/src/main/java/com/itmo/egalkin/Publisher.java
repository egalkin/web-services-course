package com.itmo.egalkin;

import org.uddi.api_v3.*;
import org.apache.juddi.api_v3.*;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.config.UDDIClient;


public class Publisher {

    private static UDDIClerk clerk = null;

    public Publisher() {
        try {
            UDDIClient uddiClient = new UDDIClient("META-INF/app.xml");
            clerk = uddiClient.getClerk("default");
            if (clerk == null)
                throw new Exception("the clerk wasn't found, check the config file!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish(String name) {
        try {
            // Creating the parent business entity that will contain our service.
            BusinessEntity myBusEntity = new BusinessEntity();
            Name myBusName = new Name();
            myBusName.setValue(name);
            myBusEntity.getName().add(myBusName);
            // Adding the business entity to the "save" structure, using our publisher's authentication info and saving away.
            BusinessEntity register = clerk.register(myBusEntity);
            if (register == null) {
                System.out.println("Save failed!");
                System.exit(1);
            }
            String myBusKey = register.getBusinessKey();
            System.out.println("myBusiness key:  " + myBusKey);

            // Creating a service to save.  Only adding the minimum data: the parent business key retrieved from saving the business
            // above and a single name.
            BusinessService myService = new BusinessService();
            myService.setBusinessKey(myBusKey);
            Name myServName = new Name();
            myServName.setValue("My Service");
            myService.getName().add(myServName);

            // Add binding templates, etc...
            BindingTemplate myBindingTemplate = new BindingTemplate();
            AccessPoint accessPoint = new AccessPoint();
            accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
            accessPoint.setValue("http://localhost:8081/DeviceService?wsdl");
            myBindingTemplate.setAccessPoint(accessPoint);
            BindingTemplates myBindingTemplates = new BindingTemplates();
            //optional but recommended step, this annotations our binding with all the standard SOAP tModel instance infos
            myBindingTemplates.getBindingTemplate().add(myBindingTemplate);
            myService.setBindingTemplates(myBindingTemplates);
            // Adding the service to the "save" structure, using our publisher's authentication info and saving away.
            BusinessService svc = clerk.register(myService);
            if (svc == null) {
                System.out.println("Save failed!");
                System.exit(1);
            }

            String myServKey = svc.getServiceKey();
            System.out.println("myService key:  " + myServKey);

            System.out.println("Success!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
