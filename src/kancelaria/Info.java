package kancelaria;
/**info interface */
public interface Info {
	/**since xboxes of different generations have the same gamepad and camera names, a default method is defined*/
	default String getinfo() {
		return "2x xbox controller and kinnect";
	}
}
