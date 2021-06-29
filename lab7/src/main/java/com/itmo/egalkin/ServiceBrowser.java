/*
 * Copyright 2001-2010 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.itmo.egalkin;

import java.util.List;

import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BindingTemplates;
import org.uddi.api_v3.BusinessDetail;
import org.uddi.api_v3.BusinessInfos;
import org.uddi.api_v3.BusinessList;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.Contacts;
import org.uddi.api_v3.Description;
import org.uddi.api_v3.DiscardAuthToken;
import org.uddi.api_v3.FindBusiness;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.api_v3.GetBusinessDetail;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceInfos;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDISecurityPortType;

/**
 * Modified version from examples
 */
public class ServiceBrowser {

    private static UDDISecurityPortType security = null;
    private static UDDIInquiryPortType inquiry = null;

    public ServiceBrowser() {
        try {
            UDDIClient client = new UDDIClient("META-INF/app.xml");
            Transport transport = client.getTransport("default");
            security = transport.getUDDISecurityService();
            inquiry = transport.getUDDIInquiryService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void search(String name) {
        try {
            String token = getAuthKey("uddi", "uddi");
            BusinessList findBusiness = getBusinessList(token, name);
            printBusinessInfo(findBusiness.getBusinessInfos());
            printBusinessDetails(findBusiness.getBusinessInfos(), token);
            printServiceDetailsByBusiness(findBusiness.getBusinessInfos(), token);

            security.discardAuthToken(new DiscardAuthToken());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BusinessList getBusinessList(String token, String name) throws Exception {
        FindBusiness fb = new FindBusiness();
        fb.setAuthInfo(token);
        org.uddi.api_v3.FindQualifiers fq = new org.uddi.api_v3.FindQualifiers();
        fq.getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);

        fb.setFindQualifiers(fq);
        Name searchName = new Name();
        searchName.setValue(name);
        fb.getName().add(searchName);
        BusinessList findBusiness = inquiry.findBusiness(fb);
        return findBusiness;

    }

    private String catBagToString(CategoryBag categoryBag) {
        StringBuilder sb = new StringBuilder();
        if (categoryBag == null) {
            return "no data";
        }
        for (int i = 0; i < categoryBag.getKeyedReference().size(); i++) {
            sb.append(keyedReferenceToString(categoryBag.getKeyedReference().get(i)));
        }
        for (int i = 0; i < categoryBag.getKeyedReferenceGroup().size(); i++) {
            sb.append("Key Ref Grp: TModelKey=");
            for (int k = 0; k < categoryBag.getKeyedReferenceGroup().get(i).getKeyedReference().size(); k++) {
                sb.append(keyedReferenceToString(categoryBag.getKeyedReferenceGroup().get(i).getKeyedReference().get(k)));
            }
        }
        return sb.toString();
    }

    private String keyedReferenceToString(KeyedReference item) {
        StringBuilder sb = new StringBuilder();
        sb.append("Key Ref: Name=").
            append(item.getKeyName()).
            append(" Value=").
            append(item.getKeyValue()).
            append(" tModel=").
            append(item.getTModelKey()).
            append(System.getProperty("line.separator"));
        return sb.toString();
    }

    private void printContacts(Contacts contacts) {
        if (contacts == null) {
            return;
        }
        for (int i = 0; i < contacts.getContact().size(); i++) {
            System.out.println("Contact " + i + " type:" + contacts.getContact().get(i).getUseType());
            for (int k = 0; k < contacts.getContact().get(i).getPersonName().size(); k++) {
                System.out.println("Name: " + contacts.getContact().get(i).getPersonName().get(k).getValue());
            }
            for (int k = 0; k < contacts.getContact().get(i).getEmail().size(); k++) {
                System.out.println("Email: " + contacts.getContact().get(i).getEmail().get(k).getValue());
            }
            for (int k = 0; k < contacts.getContact().get(i).getAddress().size(); k++) {
                System.out.println("Address sort code " + contacts.getContact().get(i).getAddress().get(k).getSortCode());
                System.out.println("Address use type " + contacts.getContact().get(i).getAddress().get(k).getUseType());
                System.out.println("Address tmodel key " + contacts.getContact().get(i).getAddress().get(k).getTModelKey());
                for (int x = 0; x < contacts.getContact().get(i).getAddress().get(k).getAddressLine().size(); x++) {
                    System.out.println("Address line value " + contacts.getContact().get(i).getAddress().get(k).getAddressLine().get(x).getValue());
                    System.out.println("Address line key name " + contacts.getContact().get(i).getAddress().get(k).getAddressLine().get(x).getKeyName());
                    System.out.println("Address line key value " + contacts.getContact().get(i).getAddress().get(k).getAddressLine().get(x).getKeyValue());
                }
            }
            for (int k = 0; k < contacts.getContact().get(i).getDescription().size(); k++) {
                System.out.println("Desc: " + contacts.getContact().get(i).getDescription().get(k).getValue());
            }
            for (int k = 0; k < contacts.getContact().get(i).getPhone().size(); k++) {
                System.out.println("Phone: " + contacts.getContact().get(i).getPhone().get(k).getValue());
            }
        }

    }

    private void printServiceDetail(BusinessService get) {
        if (get == null) {
            return;
        }
        System.out.println("Name " + listToString(get.getName()));
        System.out.println("Desc " + listToDescString(get.getDescription()));
        System.out.println("Key " + (get.getServiceKey()));
        System.out.println("Cat bag " + catBagToString(get.getCategoryBag()));
        if (!get.getSignature().isEmpty()) {
            System.out.println("Item is digitally signed");
        } else {
            System.out.println("Item is not digitally signed");
        }
        printBindingTemplates(get.getBindingTemplates());
    }

    private void printBindingTemplates(BindingTemplates bindingTemplates) {
        if (bindingTemplates == null) {
            return;
        }
        for (int i = 0; i < bindingTemplates.getBindingTemplate().size(); i++) {
            System.out.println("Binding Key: " + bindingTemplates.getBindingTemplate().get(i).getBindingKey());

            if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint() != null) {
                System.out.println("Access Point: " + bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getValue() + " type " + bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType());
                if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType() != null) {
                    if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType().equalsIgnoreCase(AccessPointType.END_POINT.toString())) {
                        System.out.println("Use this access point value as an invocation endpoint.");
                    }
                    if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType().equalsIgnoreCase(AccessPointType.BINDING_TEMPLATE.toString())) {
                        System.out.println("Use this access point value as a reference to another binding template.");
                    }
                    if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType().equalsIgnoreCase(AccessPointType.WSDL_DEPLOYMENT.toString())) {
                        System.out.println("Use this access point value as a URL to a WSDL document, which presumably will have a real access point defined.");
                    }
                    if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType().equalsIgnoreCase(AccessPointType.HOSTING_REDIRECTOR.toString())) {
                        System.out.println("Use this access point value as an Inquiry URL of another UDDI registry, look up the same binding template there (usage varies).");
                    }
                }
            }

        }
    }


    private String getAuthKey(String username, String password) {
        try {

            GetAuthToken getAuthTokenRoot = new GetAuthToken();
            getAuthTokenRoot.setUserID(username);
            getAuthTokenRoot.setCred(password);

            AuthToken rootAuthToken = security.getAuthToken(getAuthTokenRoot);
            System.out.println(username + " AUTHTOKEN = (don't log auth tokens!");
            return rootAuthToken.getAuthInfo();
        } catch (Exception ex) {
            System.out.println("Could not authenticate with the provided credentials " + ex.getMessage());
        }
        return null;
    }


    private void printBusinessInfo(BusinessInfos businessInfos) {
        if (businessInfos == null) {
            System.out.println("No data returned");
        } else {
            for (int i = 0; i < businessInfos.getBusinessInfo().size(); i++) {
                System.out.println("===============================================");
                System.out.println("Business Key: " + businessInfos.getBusinessInfo().get(i).getBusinessKey());
                System.out.println("Name: " + listToString(businessInfos.getBusinessInfo().get(i).getName()));

                System.out.println("Description: " + listToDescString(businessInfos.getBusinessInfo().get(i).getDescription()));
                System.out.println("Services:");
                printServiceInfo(businessInfos.getBusinessInfo().get(i).getServiceInfos());
            }
        }
    }

    private String listToString(List<Name> name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.size(); i++) {
            sb.append(name.get(i).getValue()).append(" ");
        }
        return sb.toString();
    }

    private String listToDescString(List<Description> name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.size(); i++) {
            sb.append(name.get(i).getValue()).append(" ");
        }
        return sb.toString();
    }

    private void printServiceInfo(ServiceInfos serviceInfos) {
        for (int i = 0; i < serviceInfos.getServiceInfo().size(); i++) {
            System.out.println("-------------------------------------------");
            System.out.println("Service Key: " + serviceInfos.getServiceInfo().get(i).getServiceKey());
            System.out.println("Owning Business Key: " + serviceInfos.getServiceInfo().get(i).getBusinessKey());
            System.out.println("Name: " + listToString(serviceInfos.getServiceInfo().get(i).getName()));
        }
    }

    private void printBusinessDetails(BusinessInfos businessInfos, String token) throws Exception {
        GetBusinessDetail gbd = new GetBusinessDetail();
        gbd.setAuthInfo(token);
        for (int i = 0; i < businessInfos.getBusinessInfo().size(); i++) {
            gbd.getBusinessKey().add(businessInfos.getBusinessInfo().get(i).getBusinessKey());
        }
        BusinessDetail businessDetail = inquiry.getBusinessDetail(gbd);
        for (int i = 0; i < businessDetail.getBusinessEntity().size(); i++) {
            System.out.println("Business Detail - key: " + businessDetail.getBusinessEntity().get(i).getBusinessKey());
            System.out.println("Name: " + listToString(businessDetail.getBusinessEntity().get(i).getName()));
            System.out.println("CategoryBag: " + catBagToString(businessDetail.getBusinessEntity().get(i).getCategoryBag()));
            printContacts(businessDetail.getBusinessEntity().get(i).getContacts());
        }
    }

    private void printServiceDetailsByBusiness(BusinessInfos businessInfos, String token) throws Exception {
        for (int i = 0; i < businessInfos.getBusinessInfo().size(); i++) {
            GetServiceDetail gsd = new GetServiceDetail();
            for (int k = 0; k < businessInfos.getBusinessInfo().get(i).getServiceInfos().getServiceInfo().size(); k++) {
                gsd.getServiceKey().add(businessInfos.getBusinessInfo().get(i).getServiceInfos().getServiceInfo().get(k).getServiceKey());
            }
            gsd.setAuthInfo(token);
            System.out.println("Fetching data for business " + businessInfos.getBusinessInfo().get(i).getBusinessKey());
            ServiceDetail serviceDetail = inquiry.getServiceDetail(gsd);
            for (int k = 0; k < serviceDetail.getBusinessService().size(); k++) {
                printServiceDetail(serviceDetail.getBusinessService().get(k));
            }
            System.out.println("................");

        }
    }
}
