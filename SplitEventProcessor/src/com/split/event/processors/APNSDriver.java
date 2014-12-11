package com.split.event.processors;

import java.util.Enumeration;
import java.util.Hashtable;
import com.relayrides.pushy.apns.ApnsEnvironment;
import com.relayrides.pushy.apns.PushManager;
import com.relayrides.pushy.apns.PushManagerFactory;
import com.relayrides.pushy.apns.RejectedNotificationListener;
import com.relayrides.pushy.apns.util.ApnsPayloadBuilder;
import com.relayrides.pushy.apns.util.SimpleApnsPushNotification;
import com.relayrides.pushy.apns.util.TokenUtil;
import com.relayrides.pushy.apns.*;

public class APNSDriver {

	PushManager<SimpleApnsPushNotification> pushManager;
	public boolean initialized;
	ApnsEnvironment env;
	
	public void initializeAPNS() throws Exception {
		
		if(!initialized) {
			
			env = new ApnsEnvironment("gateway.sandbox.push.apple.com",2195,"feedback.sandbox.push.apple.com",2196);
			
		final PushManagerFactory<SimpleApnsPushNotification> pushManagerFactory = new PushManagerFactory<SimpleApnsPushNotification>(
				//ApnsEnvironment.getProductionEnvironment(),
				//ApnsEnvironment.getSandboxEnvironment(),
				env,
				PushManagerFactory.createDefaultSSLContext(						
						"/Users/adityanarayan/Desktop/thirdTryCharm/thirdTryCharm.p12", "password"));

		pushManager = pushManagerFactory.buildPushManager();
		
		pushManager.registerRejectedNotificationListener(new MyRejectedNotificationListener());

		pushManager.start();	
		
		initialized = true;
		
		}
		
	}
	
public void sendPushNotificationWithCustomProperty(String message, Hashtable<String,String> customNameValuePairs, String deviceToken) throws Exception{
		
		final byte[] token = TokenUtil.tokenStringToByteArray(deviceToken);

		final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();

		payloadBuilder.setAlertBody(message);
		payloadBuilder.setSoundFileName("arcade.aiff");
		payloadBuilder.setBadgeNumber(1);
		
		
		Enumeration e = customNameValuePairs.keys();
		
		while(e.hasMoreElements()) {
		
		String key = (String)e.nextElement();
		
		payloadBuilder.addCustomProperty(key, customNameValuePairs.get(key));
		
		}

		String payload = payloadBuilder.buildWithDefaultMaximumLength();
		
		pushManager.getQueue().put(new SimpleApnsPushNotification(token, payload));
		
	}
	
	public void sendPushNotification(String message, String deviceToken) throws Exception{
		
		final byte[] token = TokenUtil.tokenStringToByteArray(deviceToken);

		final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();

		payloadBuilder.setAlertBody(message);
		payloadBuilder.setSoundFileName("arcade.aiff");
		payloadBuilder.setBadgeNumber(1);
		
		

		String payload = payloadBuilder.buildWithDefaultMaximumLength();
		
		pushManager.getQueue().put(new SimpleApnsPushNotification(token, payload));
		
	}
	
	public static void main(String[] args) throws Exception{
		// Auto-generated method stub
		
		
		
			String deviceToken = "<5cf63347 7edaf7c1 1f1159b5 e173723d ffd0261a 80921665 9f42d56d dca38a63>";
								   
			APNSDriver apns = new APNSDriver();			
			apns.initializeAPNS();
			
			String h = apns.env.getSandboxEnvironment().getApnsGatewayHost();
			int p = apns.env.getProductionEnvironment().getApnsGatewayPort();
			
			System.out.println(h+":"+p);
		
			String messageBody =  "You were tagged by somebody in a poll!";
			
			apns.sendPushNotification(messageBody, deviceToken);
	}

}


class MyRejectedNotificationListener implements RejectedNotificationListener<SimpleApnsPushNotification> {

    @Override
    public void handleRejectedNotification(
            final PushManager<? extends SimpleApnsPushNotification> pushManager,
            final SimpleApnsPushNotification notification,
            final RejectedNotificationReason reason) {

        System.out.format("[  "+new java.util.Date().toString()+" Push notification failed]\n %s was rejected with rejection reason %s\n", notification, reason);
    }
}