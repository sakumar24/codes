/*******************************************************************************
 * Copyright � 2014 Progress Software Corporation.  All Rights Reserved.
 ******************************************************************************/
package com.progress.cloudservices.SystemSelfTest;

import java.io.InputStream;
import java.util.Properties;

/**
 * Constants class which declares all the common constants used in the Cloud
 * Services.
 * 
 * @author dprasad
 * @since 0.5
 */
public class CommonServicesConstants {

	public static final String MODULE_NAME_MAIL_PROCESSING = "CS-MAIL-PROCESSING";
	public static final String MODULE_NAME_NOTIFICATION_PROCESSING = "CS-NOTIFICATION-PROCESSOR";
	public static final String DEFAULT_TENANT_NAME = "DEFAULT_TENANT";
	public static final String DEFAULT_TEMPLATE_NAME = "DEFAULT_TEMPLATE";
	
	public static final String DOMAIN_MODEL_PACKAGE = "com.progress.cloudservices.common.domain.model";
	public static final String JDBC_JNDI_PROPERTY = "jdbc.jndi";
	public static final String DATABASE_URL_PROPERTY = "jdbc.databaseurl";
	public static final String DRIVER_CLASS_NAME = "jdbc.driverClassName";
	public static final String DATABASE_USER_NAME_PROPERTY = "jdbc.username";
	public static final String DATABASE_PASSWORD_PROPERTY = "jdbc.password";
	public static final String HIBERNATE_DIALECT_PROPERTY = "hibernate.dialect";
	public static final String HIBERNATE_SHOW_SQL_PROPERTY = "hibernate.show_sql";
	public static final String HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS_PROPERTY = "current_session_context_class";
	public static final String HIBERNATE_CONNECTION_DATASOURCE = "hibernate.connection.datasource";
	public static final String DATABASE_MAX_ACTIVE_PROPERTY = "datasource.max.active";
	public static final String DATABASE_MAX_IDLE_PROPERTY = "datasource.max.idle";
	public static final String DATABASE_MIN_IDLE_PROPERTY ="datasource.min.idle";
	public static final String DATABASE_MAX_WAIT_PROPERTY = "datasource.max.wait";
	public static final String DATABASE_INTIAL_SIZE_PROPERTY = "datasource.initial.size";
	public static final String DATABASE_TIME_BETWEEN_EVICTION_PROPERTY = "datasource.timeBetweenEvictionRunsMillis";
	public static final String MAIL_PROCESSOR_READ_SIZE_PROPERTY = "mail.processor.read.size";
	public static final String UN_PROCESSED = "UNPROCESSED";
	public static final String IN_PROCESS = "INPROCESS";
	public static final String ERROR_TRANSLATION = "ERRORTRANSLATION";
	public static final String ERROR_PARSING = "ERRORPARSING";
	public static final String ERROR_SENDING = "ERRORSENDING";
	public static final String UNKNOWN = "UNKNOWN";
	public static final String ARCHIVED = "ARCHIVED";
	public static final String ERROR_PROCESSING = "ERRORPROCESSING";
	public static final String ERROR_MARSHALLING = "ERRORMARSHALLING";
	public static final String SUPPORT_OFFERING_URL="SUPPORT_OFFERING_URL";
	public static final String PROGRESS_COMMUNITY_URL="PROGRESS_COMMUNITY_URL";
	public static final String PORTAL_URL="PORTAL_URL";
	public static final String NOT_FOR_US = "NOTFORUS";
	public static final String FAILED = "FAILED";
	
	public static final String FROM_ADDRESS = "noreply@progress.com";
	
	public static final String DATE_FORMAT;
	
	public static final String DATE_ONLY_FORMAT="yyyy-MM-dd";

	public static final String PLAN_NAME_DEFAULT = "DEFAULT";
	
	public static final String PLAN_NAME_EVAL;
	
	public static final String UTF8_CHARACTER_ENCODING = "UTF-8";
	
	public static final int EVAL_90 = 90;
	public static final int EVAL_30 = 30;
	public static final String SUPPLEMENTAL_PLAN = "supplemental plan";
	public static final String SUPPLEMENTAL_FIELD = "supplemental field";
	public static final String REG_EX_MATCH = "supplementalPlanRegexMatch";

	public static final String IS_SHIFTING_FROM_PAYPAL_TO_ARIA = "PAYPAL_TO_ARIA";
	public static final String CS_ADDITIONAL_PLAN_DETAILS_KEY = "cloud.services.additionalPlanItems.details";
	public static final String CS_ROLE_TO_LIMITS_MAPPING_KEY = "role.to.limits.mapping";
	public static final String CS_ADDONS_PLAN_ITEMS="cloud.services.addon.plan.items";
	
	// Hibernate Properties for spring
	public static final String HIBERNATE_DIALECT = "${"+ HIBERNATE_DIALECT_PROPERTY + "}";
	public static final String JNDI_NAME = "${" + JDBC_JNDI_PROPERTY + "}";
	public static final String DRIVER_CLASS="${"+DRIVER_CLASS_NAME+"}";
	public static final String DATABASE_URL="${"+DATABASE_URL_PROPERTY+"}";
	public static final String USER_NAME="${"+DATABASE_USER_NAME_PROPERTY+"}";
	public static final String PASSWORD="${"+DATABASE_PASSWORD_PROPERTY+"}";
	public static final String MAX_ACTIVE="${"+DATABASE_MAX_ACTIVE_PROPERTY+"}";
	public static final String MAX_IDLE="${"+DATABASE_MAX_IDLE_PROPERTY+"}";
	public static final String MIN_IDLE="${"+DATABASE_MIN_IDLE_PROPERTY+"}";
	public static final String MAX_WAIT="${"+DATABASE_MAX_WAIT_PROPERTY+"}";
	public static final String INITIAL_SIZE="${"+DATABASE_INTIAL_SIZE_PROPERTY+"}";
	public static final String TIME_BETWEEN_EVICTION="${"+DATABASE_TIME_BETWEEN_EVICTION_PROPERTY+"}";
	
	// Mail Processor Properties for Spring
	public static final String MAIL_PROCESSOR_READ_SIZE="${"+MAIL_PROCESSOR_READ_SIZE_PROPERTY+"}";
	public static final String PLAN_NAME_BASIC_PROPERTY = "cloud.services.easyl.basic.plan";
	public static final String PRODUCT_SPECIFIC_FIELDS = "ProductSpecificFields";	
	
	// START Eloqua properties
	public static final String ELOQUA_WEBSERVICE_URL = "${" + "eloqua.webservice.url" + "}";
	public static final String ELOQUA_WEBSERVICE_USERNAME = "${" + "eloqua.webservice.username" + "}";
	public static final String ELOQUA_WEBSERVICE_PASSWORD = "${" + "eloqua.webservice.password" + "}";
	public static final String ELOQUA_WEBSERVICE_TIMEOUT = "${" + "eloqua.webservice.timeout" + "}";
	
	public static final String ELOQUA_TENANT_PARAM_NAME = "sendDetailsToEloqua";
	
	public static final String ELOQUA_PLAN_PARAM_NAME = "eloqua.plan.name.param";
	public static final String ELOQUA_DEFAULT_PLAN_PARAM_NAME = "eloqua.plan.name.param.default";
	public static final String ELOQUA_SUBSCRIPTION_LEVEL_PARAM_NAME = "eloqua.subscription.level.param";
	public static final String ELOQUA_DATASOURCES_PARAM_NAME = "eloqua.datastore.param";
	public static final String ELOQUA_CUSTOMER_NUMBER_PARAM_NAME = "eloqua.customer.number.param";
	public static final String ELOQUA_DOMAIN_NUMBER_PARAM_NAME = "eloqua.domain.number.param";
	public static final String ELOQUA_ADDONS_PARAM_NAME = "eloqua.addons.param";
	public static final String ATC_OVERRIDE_PARAM_NAME = "ATCoverride";
	public static final String AGREEMENT_FLAG_PARAM_NAME = "agreementFlag";	
	public static final String NEXT_URL_PARAM_NAME = "nextUrl";
	public static final String PRODUCT_URL_PARAM_NAME = "productUrl";
	public static final String ORIGIN_URL_PARAM_NAME = "originUrl";
	public static final String REGISTERED_FLAG__PARAM_NAME = "newUserRegistered";
	public static final String PRODUCT_PARAM_NAME = "productName";
	public static final String IGNORE_COOKIE_PARAM_NAME = "ignoreCookie";
	public static final String REGISTERED_PARAM_NAME = "registered";
	public static final String PRODUCT_KEY_NAME = "productParamName";
	public static final String NEXT_URL_KEY_NAME = "nextUrlParamName";
	public static final String REGISTERED_KEY_NAME = "registeredParamName";
	public static final String PRODUCT_URL_KEY_NAME = "productUrlParamName";
	public static final String ORIGIN_URL_KEY_NAME = "originUrlParamName";
	public static final String IGNORE_COOKIE_KEY_NAME = "ignoreCookieParamName";
	public static final String CONSOLE_FAKE_BACK_CARD = "fake.card.back.data";
	public static final String CONSOLE_FAKE_FRONT_CARD = "fake.card.front.data";
	public static final String SAML_REDIRECT_URL = "samlRedirect";
	public static final String SAML_VERIFIED_REDIRECT_URL = "samlVerifiedRedirect";
	public static final String REQUEST_REDIRECTION_MAP = "requestRedirectionMap";
	public static final String UI_SERVICE_SUBSCRIBE_URL_PARAM_NAME = "uiServiceSubscribeUrl";
	public static final String SIGNUP_URL_KEY_NAME = "SignUpUrl";
	public static final String LOGOUT_URL = "logoutUrl";
	public static final String SHOW_BUY_NOW_URL = "showBuyNow";
	
	
	public static final String ATC = "atc";
	public static final String UID = "uid";

	// Set of properties for posting billing address to Eloqua
	public static final String ELOQUA_BILLING_PARAM_MAIL = " C_Billing_Email_Address1";
	public static final String ELOQUA_BILLING_PARAM_LAST_NAME = "C_Billing_Last_Name1";
	public static final String ELOQUA_BILLING_PARAM_POSTAL_CODE = "C_Billing_Postal_Code";
	public static final String ELOQUA_BILLING_PARAM_COMPANY = "C_Billing_Company1";
	public static final String ELOQUA_BILLING_PARAM_COUNTRY = "C_Billing_Country";
	public static final String ELOQUA_BILLING_PARAM_CITY = "C_Billing_City";
	public static final String ELOQUA_BILLING_PARAM_FIRST_NAME = "C_Billing_First_Name1";
	public static final String ELOQUA_BILLING_PARAM_STATE = "C_Billing_State";
	public static final String ELOQUA_BILLING_PARAM_ADDR1 = "C_Billing_Address1";
	public static final String ELOQUA_BILLING_PARAM_ADDR2 = "C_Billing_Address2";
	public static final String ELOQUA_BILLING_PARAM_ARIA_ACC_NUM = "C_Aria_Account_Number1";
	// public static final String ELOQUA_BILLING_PARAM_PROGRESS_ID = "C_Progress_ID1";
	
	// D2C leads
	public static final String ELOQUA_D2C_ALL_DATASOURCES = "All";
	
	// END Eloqua properties
	
	// UI Session token properties
	public static final String TOKEN_EXPIRY_PERIOD = "token_expiry_time";
	public static final String TOKEN_REMOVAL_PERIOD = "delete_token_time";
	
	// PCN Generation Details
	public static final String PCN_GENERATION_URL = "pcn.generation.url";
	public static final String PCN_AUTH_USERNAME = "pcn.basicauth.user";
	public static final String PCN_AUTH_PASSWORD = "pcn.basicauth.password";
	
	public static final String BILLING_ID = "BILLING_ID";
    public static final String PCN = "PCN";
    public static final String COMPANY_NAME = "COMPANY_NAME";
    public static final String EMAIL_ADDRESS = "EMAIL_ADDRESS";
    public static final String REGISTERATION_DATE = "REGISTERATION_DATE";
    public static final String PROGRESS_ID = "PROGRESS_ID";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String ACCOUNT_ID = "ACCOUNT_ID";
    public static final String TENANT_TYPE ="TENANT_TYPE";
    public static final String HEADER_IMG_URL ="HEADER_IMG_URL";
    public static final String ADDRESS1 ="ADDRESS1";
    public static final String ADDRESS2 ="ADDRESS2";
    public static final String STREET ="STREET";
    public static final String CITY ="CITY";
    public static final String LOCALITY ="LOCALITY";
    public static final String STATE ="STATE";
    public static final String COUNTRY ="COUNTRY";
    public static final String POSTAL_CODE ="POSTAL_CODE";
    public static final String PHONE ="PHONE";
    public static final String PARENT_ACCOUNT_ID = "PARENT_ACCOUNT_ID";
    public static final String PARENT_PROGRESS_ID = "PARENT_PROGRESS_ID";
    public static final String PARENT_EMAIL_ADDRESS = "PARENT_EMAIL_ADDRESS";
    
    
    
    
    public static final String FORMAT_HTML = "html";
    
	public static final String MAIL_ENDPOINT_URL="cs.mailing.endpoint.url";
	public static final String DEFAULT_TENANT_USERNAME="default.tenant.username";
	public static final String DEFAULT_TENANT_PASSWORD="default.tenant.password";
	public static final String MAIL_ENDPOINT_CONTENT_TYPE="cs.mailing.content.type";
	public static final String MAIL_FROM_ADDRESS_DISPLAY_NAME = "fromAddressDisplayName";
	
	public static final String ACCOUNT_ENDPOINT_URL = "cs.account.endpoint.url";
    
    public static final String MAIL_TEMPLATE_HEADER_IMG_URL="mail.template.param.header.image.url";
    
    public static final String MAIL_TEMPLATE_FORUM_URL = "mail.template.param.forum.url";
    public static final String MAIL_TEMPLATE_SUBSCRIPTION_URL = "mail.template.param.subscription.url";
    public static final String MAIL_TEMPLATE_OVERAGE_SQL_CHARGES = "mail.template.param.overage.charges.sql";
    public static final String MAIL_TEMPLATE_OVERAGE_DATA_CHARGES = "mail.template.param.overage.charges.data";
    
    public static final String LEVEL_OF_SUPPORT_WEB_PORTAL ="WebPortal";
    public static final String LEVEL_OF_SUPPORT_PHONE ="Phone";
    public static final String MAIL_ID_REGISTRATION="cs.registrations.mail.id";

	//Start TMS properties
	public static final String TMS_TENANT_PARAM_NAME = "${" + "cs.tenant.tms.param" + "}";
	
    
    public static final String DASHBOARD_URL ="cs.uiservice.pacific.dashboard.url";
    public static final String FOOTER_INFO ="cs.uiservice.footer.info";
	
    public static final String ARIA_COLLECTION_GROUP_ID = "aria.collection.group.id";
    
	public static final String TENANT_CONFIG_FILE_NAME = "tenant";
	/*changed*/
	public static final String TENANT_CONFIG_BASE_PACKAGE = "com.progress.cloudservices.CommonTenantConfig";
	public static final String TENANT_CONFIG_CACHE_IN_MIN = "tenant.config.cache.time.mins"; 
	
	public static final String ARIA_ACCOUNT_LEVEL_OF_SUPPORT = "LevelOfSupport";
	
	public static final String COMMUNITY_SUPPORT = "Community";
	public static final String PHONE_SUPPORT = "Phone";
	public static final String PREMIUM_SUPPORT = "Premium";
	
	public static final String TENANT_HELP_URL_PROP_NAME = "tenant.help.url";
	
	public static final String DATE_WITH_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	//Metering properties
	public static final String USAGE_TYPE_ABSOLUTE="absolute";
	public static final String USAGE_TYPE_INCREMENTAL="incremental";
	
	public static final String  ACCOUNT_TYPES_FOR_AGGREGATED_USAGE = "accountTypesForAggregatedUsage";

	/**
	 * Tenant additional property key for customer id(used for Rollbase).
	 */
	public static final String SU_CUSTOMER_ID = "suCustomerId";
	public static final String ARIA_DEFAULT_PARENT_PLAN_NAME = "ARIA_DEFAULT_PARENT_PLAN_NAME";
	public static final String ARIA_DEFAULT_PARENT_PLAN_GROUP = "ARIA_DEFAULT_PARENT_PLAN_GROUP";
	public static final String SUPPORT_COUNT = "SUPPORT_COUNT";
	
	public static final String ROLLBASE_DEVELOPER_USER_MIN_COUNT="rollbase.developer.plan.support.entitlement.user.count";
	public static final String ROLLBASE_DEVELOPER_ENTITLEMENT_COUNT="rollbase.developer.plan.support.entitlement.count";
	public static final String ROLLBASE_BASIC_USER_MIN_COUNT="rollbase.basic.plan.support.entitlement.user.count";
	public static final String ROLLBASE_BASIC_ENTITLEMENT_COUNT="rollbase.basic.plan.support.entitlement.count";
	public static final String ROLLBASE_PROFESSIONAL_USER_MIN_COUNT="rollbase.professional.plan.support.entitlement.user.count";
	public static final String ROLLBASE_PROFESSIONAL_ENTITLEMENT_COUNT="rollbase.professional.plan.support.entitlement.count";
	
	/**
	 * Tenant additional property key for existing datasources(used for D2c).
	 */
	public static final String D2C_DATASOURCES = "free_datasources";
	   
	
	
	public static final String CS_VERSION = "csversion";
   
	public static final String FIELD_UPDATED_PLAN = "PLAN";
	public static final String FIELD_UPDATED_ADDON = "ADDON";
	
	public static final String CS_USAGE_STAT_LOGIN = "CS-USAGE-STAT-LOGIN";
	public static final String CS_USAGE_STAT_PRODUCT = "CS-USAGE-STAT-PRODUCT";
	
	public final static String EMAIL_SUBJECT = "_mail_subject"; // this constant is to append at the end of the templete and get the subject out of it
	
    static {
          String dateFormat = "";
          
          String evalPlanName = "";
          try {
                InputStream is = CommonServicesConstants.class.getResourceAsStream("/cloud-services.properties");
                if (is != null) {
                      Properties props = new Properties();
                      props.load(is);
                      dateFormat = props.getProperty("cloud.services.date.format");
                      evalPlanName = props.getProperty("cloud.services.default.eval.plan");
                }
          } catch (Exception e) {
                // ignore
          }
          if (dateFormat == null || dateFormat.trim().length() == 0){
        	  DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
          } else {
        	  DATE_FORMAT = dateFormat.trim();
          }
          
          if (evalPlanName == null || evalPlanName.trim().length() == 0){
        	  PLAN_NAME_EVAL = "Evaluation";
          } else {
        	  PLAN_NAME_EVAL = evalPlanName.trim();
          }          
    }

    public static final String APPLICATION_CONTENT_TYPE = "application/json";
    public static final String PARENT_BILLING_ID = "PARENT_BILLING_ID";
    public static final String CHILDREN_BILLING_ID = "CHILDREN_BILLING_ID";
    public static final String SUPPORT_EMAIL = "SUPPORT_EMAIL";
    public static final  String forumUrl="forumUrl";
    public static final String D2C_CUST_SUPPORT_CONTACT_NO="D2C_CUST_SUPPORT_CONTACT_NO";
    public static final String TRIM_PREFIX_D2C_DATASTORE_NAME="TRIM_DATA_STORE_NAME_PREFIX";
    // Used by LoggingAspect to obtain the Tenant/Client request ID.
    public static final String T_REQ_ID = "T_REQ_ID";
    public static final String X_T_REQ_ID = "X_T_REQ_ID";
    public static final String USAGE_LIMITS_EXCEEDED = "Usage Limits Exceeded";
    
    public static final String PLAN_NAME_EVALUATION = "Evaluation";
    public static final String PLAN_NAME_PROGRESS_INTERNAL="Individual Progress Internal";

	public static final String BILLING_ACCOUNT_THERSHOLD_VALUE = "billing.account.threshold.value";
    public static final String STOP_ON_LIMIT_REACHED="StopOnLimitReached";
	public static final String DEFAULT_BILLING_ID_WHEN_NOT_UPLOAD_TO_BILLING_SYSTEM = "1";
	public static final String METERING_USE_BILLING_SYSTEM="${metering.use.billing.system}";
    public static final String PROP_STOP_ON_LIMIT_REACHED="${account.lock.limit.exceeded}";
	public static final String STOP_ON_OVERAGE_FIELD_NAME = "stopOnOverage";


	

}
