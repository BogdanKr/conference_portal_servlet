package ua.krasun.conference_portal_servlet.model.dao;

import ua.krasun.conference_portal_servlet.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory  {
    private static DaoFactory daoFactory;
    public abstract UserDao createUserDao();
    public abstract ConferenceDao createConferenceDao();
    public abstract PresentationDao createPresentationDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
