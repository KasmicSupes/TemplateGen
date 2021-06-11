package com.example.template_db_v4;

import java.util.ArrayList;

import org.openapitools.jackson.nullable.JsonNullable;
@SuppressWarnings("FieldMayBeFinal")
public class TemplateUpdateDto {
	private JsonNullable<Integer> id = JsonNullable.undefined();
	private JsonNullable<String> organisation_id = JsonNullable.undefined();
    private JsonNullable<String> type = JsonNullable.undefined();
    private JsonNullable<String> content = JsonNullable.undefined();
    private JsonNullable<ArrayList<String>> placeholders = JsonNullable.undefined();
    protected TemplateUpdateDto() {}
	public JsonNullable<Integer> getId() {
		return id;
	}
	public JsonNullable<String> getOrganisation_id() {
		return organisation_id;
	}
	public JsonNullable<String> getType() {
		return type;
	}
	public JsonNullable<String> getContent() {
		return content;
	}
	public JsonNullable<ArrayList<String>> getPlaceholders() {
		return placeholders;
	}
}
