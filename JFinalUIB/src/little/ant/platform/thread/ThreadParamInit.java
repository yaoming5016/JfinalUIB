package little.ant.platform.thread;

import java.util.List;

import little.ant.platform.model.Dict;
import little.ant.platform.model.Group;
import little.ant.platform.model.Operator;
import little.ant.platform.model.Param;
import little.ant.platform.model.Role;
import little.ant.platform.model.Station;
import little.ant.platform.model.User;
import little.ant.platform.tools.ToolSqlXml;

import org.apache.log4j.Logger;

/**
 * 系统初始化缓存操作类
 * @author 董华健  2012-10-16 下午1:16:56
 */
public class ThreadParamInit extends Thread {
	
	private static Logger log = Logger.getLogger(ThreadParamInit.class);
	
	public static String cacheStart_user = "user_";
	public static String cacheStart_group = "group_";
	public static String cacheStart_role = "role_";
	public static String cacheStart_station = "station_";
	public static String cacheStart_operator = "operator_";
	public static String cacheStart_dict = "dict_";
	public static String cacheStart_dict_child =  "dict_child_";
	public static String cacheStart_param = "param_";
	public static String cacheStart_param_child =  "param_child_";
	
	@Override
	public void run() {
		cacheAll();
	}
	
	/**
	 * 缓存所有参数
	 */
	public static synchronized void cacheAll(){
		log.info("缓存参数初始化 start ...");

		// 1.缓存用户
		platform_cacheUser();

		// 2.缓存组
		platform_cacheGroup();

		// 3.缓存角色
		platform_cacheRole();

		// 4.缓存岗位
		platform_cacheStation();

		// 5.缓存功能
		platform_cacheOperator();

		// 6.缓存字典
		platform_cacheDict();

		// 6.缓存参数
		platform_cacheParam();

		log.info("缓存参数初始化 end ...");
	}

	/**
	 * 缓存所有用户
	 * @author 董华健    2012-10-16 下午1:16:48
	 */
	public static void platform_cacheUser() {
		String sql = ToolSqlXml.getSql("platform.user.all");
		List<User> userList = User.dao.find(sql);
		for (User user : userList) {
			User.dao.cacheAdd(user.getStr("ids"));
			user = null;
		}
		userList = null;
	}

	/**
	 * 缓存所有组
	 * @author 董华健    2012-10-16 下午1:17:20
	 */
	public static void platform_cacheGroup() {
		String sql = ToolSqlXml.getSql("platform.group.all");
		List<Group> groupList = Group.dao.find(sql);
		for (Group group : groupList) {
			Group.dao.cacheAdd(group.getStr("ids"));
		}
		groupList = null;
	}

	/**
	 * 缓存所有角色
	 * @author 董华健    2012-10-16 下午1:17:20
	 */
	public static void platform_cacheRole() {
		String sql = ToolSqlXml.getSql("platform.role.all");
		List<Role> roleList = Role.dao.find(sql);
		for (Role role : roleList) {
			Role.dao.cacheAdd(role.getStr("ids"));
		}
		roleList = null;
	}
	
	/**
	 * 缓存所有的岗位
	 * @author 董华健    2013-07-16 下午1:17:20
	 */
	public static void platform_cacheStation() {
		String sql = ToolSqlXml.getSql("platform.station.all");
		List<Station> stationList = Station.dao.find(sql);
		for (Station station : stationList) {
			Station.dao.cacheAdd(station.getStr("ids"));
		}
		stationList = null;
	}

	/**
	 * 缓存操作
	 * @author 董华健    2012-10-16 下午1:17:12
	 */
	public static void platform_cacheOperator() {
		String sql = ToolSqlXml.getSql("platform.operator.all");
		List<Operator> operatorList = Operator.dao.find(sql);
		for (Operator operator : operatorList) {
			Operator.dao.cacheAdd(operator.getStr("ids"));
			operator = null;
		}
		operatorList = null;
	}

	/**
	 * 缓存业务字典
	 * @author 董华健    2012-10-16 下午1:17:04
	 */
	public static void platform_cacheDict() {
		String sql = ToolSqlXml.getSql("platform.dict.all");
		List<Dict> dictList = Dict.dao.find(sql);
		for (Dict dict : dictList) {
			Dict.dao.cacheAdd(dict.getStr("ids"));
			dict = null;
		}
		dictList = null;
	}

	/**
	 * 缓存业务参数
	 * @author 董华健    2012-10-16 下午1:17:04
	 */
	public static void platform_cacheParam() {
		String sql = ToolSqlXml.getSql("platform.param.all");
		List<Param> paramList = Param.dao.find(sql);
		for (Param param : paramList) {
			Param.dao.cacheAdd(param.getStr("ids"));
			param = null;
		}
		paramList = null;
	}

}