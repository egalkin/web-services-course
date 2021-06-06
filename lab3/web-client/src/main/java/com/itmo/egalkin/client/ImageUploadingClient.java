package com.itmo.egalkin.client;

import com.itmo.egalkin.generated.DeviceService;

import javax.imageio.ImageIO;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author egalkin
 * @since 06.06.2021
 */
public class ImageUploadingClient {

    public static void main(String[] args) throws IOException {
        DeviceService deviceService = new DeviceService();
        Image imgToUpload = ImageIO.read(new File("duke.png"));
        BindingProvider bp = (BindingProvider) deviceService.getDeviceWebServicePort();
        SOAPBinding binding = (SOAPBinding) bp.getBinding();
        binding.setMTOMEnabled(true);
        String uploadStatus = deviceService.getDeviceWebServicePort().uploadDeviceImageMock(imgToUpload);
        System.out.println(uploadStatus);
    }
}
