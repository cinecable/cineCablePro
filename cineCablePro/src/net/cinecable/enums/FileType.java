package net.cinecable.enums;

public enum FileType {

	TEXT("txt"), ZIP("application/zip");

	private String _desc;

	FileType(String des) {
		this._desc = des;
	}

	public String getDescription() {
		return this._desc;
	}
}
