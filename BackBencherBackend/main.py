from fastapi import FastAPI
from youtube_transcript_api import YouTubeTranscriptApi
import openai
import prompts

openai.api_key = "put your apikey here"
app = FastAPI()


def process_transcript(transcript):
    combined_text = " ".join(item["text"] for item in transcript)
    return combined_text


@app.get("/idea")
async def generate_lecture_notes():
    return "Hello"

@app.get("/fetch_subtitles")
async def fetch_subtitles(video_url: str):
    try:
        video_id = video_url.split("v=")[1]
        transcript = YouTubeTranscriptApi.get_transcript(video_id)
        processed_transcript = process_transcript(transcript)
        response =openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=[
                 {"role": "user", "content": f"${prompts.promptQuestions} ${processed_transcript}"},
            ]
        )

        lecture_notes = response['choices'][0]['message']['content']
        return lecture_notes
    except Exception as e:
        return {"error": str(e)}

@app.get("/fetch_notes")
async def fetch_subtitles(video_url: str):
    try:
        video_id = video_url.split("v=")[1]
        transcript = YouTubeTranscriptApi.get_transcript(video_id)
        processed_transcript = process_transcript(transcript)
        response =openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=[
                 {"role": "user", "content": f"${prompts.promptNotes} ${processed_transcript}"},
            ]
        )

        lecture_notes = response['choices'][0]['message']['content']
        return lecture_notes
    except Exception as e:
        return {"error": str(e)}

@app.get("/fetch_summary")
async def fetch_subtitles(video_url: str):
    try:
        video_id = video_url.split("v=")[1]
        transcript = YouTubeTranscriptApi.get_transcript(video_id)
        processed_transcript = process_transcript(transcript)
        response =openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=[
                 {"role": "user", "content": f"${prompts.promptSummary} ${processed_transcript}"},
            ]
        )

        lecture_notes = response['choices'][0]['message']['content']
        return lecture_notes
    except Exception as e:
        return {"error": str(e)}









