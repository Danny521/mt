package com.danny.config;

//public class WebConfig {
//    @Bean
//    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//        registration.getUrlMappings().clear();
//        registration.addUrlMappings("/");
//        registration.setLoadOnStartup(1);
//        registration.setName("myDispatcher");
//        return registration;
//    }
//
//    @Bean
//    public Filter characterEncodingFilter() {
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//        return characterEncodingFilter;
//    }
//
//    @Bean
//    public FilterRegistrationBean LoginFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new LoginFilter());
//        registration.setEnabled(true);
//        registration.addUrlPatterns("/*");
//        registration.setName("login filter");
//        Map<String, String> initParameters = new HashMap<>();
//        initParameters.put("ENCODING", "UTF-8");
//        initParameters.put("LOGIN_PATH", "/login");
//        initParameters.put("FILTER_PATH",
//                "alarmCenter#login#mlogin#logout#product#certify#pvp#403#404#500#heart/beat#get_finger_users#keyLogin#swagger#api#get_all_orgs");
//        registration.setInitParameters(initParameters);
//        return registration;
//    }
//
//    @Bean
//    public FilterRegistrationBean PermissionFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new PermissionFilter());
//        registration.setEnabled(true);
//        registration.addUrlPatterns("/*");
//        registration.setName("permission filter");
//        return registration;
//    }
//}
