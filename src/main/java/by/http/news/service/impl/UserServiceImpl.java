package by.http.news.service.impl;

import by.http.news.bean.User;
import by.http.news.bean.UserData;
import by.http.news.dao.DAOException;
import by.http.news.dao.DAOProvider;
import by.http.news.dao.UserDAO;
import by.http.news.service.ServiceException;
import by.http.news.service.UserService;
import by.http.news.util.CheckField;
import by.http.news.util.UserDataField;
import by.http.news.util.UtilException;

public class UserServiceImpl implements UserService {

	private static final DAOProvider provider = DAOProvider.getInstance();

	private static final UserDAO userDAO = provider.getUserDAO();

	private static final String EXP_SYMBOLS = ".*\\W+.*";
	private static final String EXP_EMAIL = "\\w{3}\\w*@\\w{3}\\w*\\.\\w{2}\\w*";

	@Override
	public void registration(UserData userData) throws ServiceException {

		checkField(userData);

		try {

			userDAO.registration(userData);

		} catch (DAOException e) {

			throw new ServiceException(e.getMessage(), e);
		}

	}

	@Override
	public void update(UserData userData) throws ServiceException {

		userData.setPassword("noNeedToUpdate");

		checkField(userData);

		try {

			userDAO.update(userData);

		} catch (DAOException e) {

			throw new ServiceException(e.getMessage(), e);
		}

	}

	@Override
	public void delete(UserData userData) throws ServiceException {
		
		try {

			String key = UserDataField.LOGIN.toString();
			String value = userData.getLogin();
			
			CheckField.checkKVLMin(key, value, 3);
			CheckField.checkKVE(key, value, EXP_SYMBOLS);

			userDAO.delete(userData);

		} catch (DAOException | UtilException e) {

			throw new ServiceException(e.getMessage(), e);
		}

	}

	@Override
	public void password(UserData userData) throws ServiceException {

		try {

			CheckField.checkKVLMin(UserDataField.PASSWORD.toString(), userData.getPassword(), 8);

			userDAO.password(userData);

		} catch (DAOException | UtilException e) {

			throw new ServiceException(e.getMessage(), e);
		}

	}

	@Override
	public User authorization(UserData userData) throws ServiceException {
		
		try {
			
			String key = UserDataField.LOGIN.toString();
			String value = userData.getLogin();

			CheckField.checkKVLMin(key, value, 3);
			CheckField.checkKVE(key, value, EXP_SYMBOLS);
			
			CheckField.checkKVLMin(UserDataField.PASSWORD.toString(), userData.getPassword(), 3);

			return userDAO.authorization(userData);

		} catch (DAOException | UtilException e) {

			throw new ServiceException(e.getMessage(), e);
		}

	}

	private void checkField(UserData userData) throws ServiceException {
		
		try {
			
			String key = UserDataField.LOGIN.toString();
			String value = userData.getLogin();

			CheckField.checkKVLMin(key, value, 3);
			CheckField.checkKVE(key, value, EXP_SYMBOLS);
			
			key = UserDataField.AGE.toString();
			value = userData.getAge();
			
			CheckField.checkKVLMin(key, value, 1);
			CheckField.checkKVLMax(key, value, 3);
			CheckField.checkKI(key, value);

			CheckField.checkKVLMin(UserDataField.PASSWORD.toString(), userData.getPassword(), 3);

			
			if (!CheckField.checkKVN(userData.getEmail())) {

				CheckField.checkKVE(UserDataField.EMAIL.toString(), userData.getEmail(), EXP_EMAIL);
			}
			
			if (!CheckField.checkKVN(userData.getName())) {

				CheckField.checkKVE(UserDataField.NAME.toString(), userData.getName(), EXP_SYMBOLS);
			}

		} catch (UtilException e) {

			throw new ServiceException(e.getMessage(), e);
		}

	}

}
